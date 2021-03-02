package com.degea9.android.food.foodrecipe.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.degea9.android.food.foodrecipe.search.databinding.FragmentSearchBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    //controller for epoxy recyclerview
    private val pagingController = SearchResultPagingController(::onItemClick)
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        search(query)
        binding.edtSearch.setText(query)
    }

    private fun focusOnSearchBar(){
        binding.edtSearch.requestFocus()
        showKeyboard()
    }

    fun showKeyboard(){

        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE)  as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
    fun hideKeyboard(){
        val view = requireView()
        view.let {
            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE)  as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    private fun setup(){
        focusOnSearchBar()
        binding.rvSearchResult.adapter = pagingController.adapter
        binding.rvSearchResult.setItemSpacingPx(30)
        binding.edtSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                hideKeyboard()
                true
            } else {
                false
            }
        }
        binding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRepoListFromInput() {
        binding.edtSearch.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun search(query: String) {
        if (query != DEFAULT_QUERY) {
            //cancel the previous job before creating a new one
            searchJob?.cancel()
            searchJob = viewLifecycleOwner.lifecycleScope.launch {
                searchViewModel.searchRecipe(query)?.collectLatest {
                    pagingController.submitData(it)
                }
            }
        }
    }

    private fun onItemClick(recipe: Recipe?){
        recipe?.let {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(
                    it.id
                )
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, binding.edtSearch.text.trim().toString())
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }
}