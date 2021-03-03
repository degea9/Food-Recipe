package com.degea9.android.food.foodrecipe.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.degea9.android.food.foodrecipe.search.databinding.FragmentSearchBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SearchFragment(override val coroutineContext: CoroutineContext = Dispatchers.Main) : BaseFragment(), CoroutineScope  {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    //controller for epoxy recyclerview
    private val pagingController = SearchResultPagingController(::onItemClick)

    private val suggestionController = SuggestionController(::onSuggestionClick)
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
        setUpObserver()
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
    private fun setupEditTextSearch(){
        val watcher = object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                launch {
                    delay(300)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch
                    searchViewModel.getSuggestKeyword(s.toString(), SUGGESTION_NUMBER)
                    // do our magic here
                }
            }
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }
        binding.edtSearch.addTextChangedListener(watcher)
    }
    private fun setup(){
        setupEditTextSearch()
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

        binding.rvSearchSuggestion.adapter = suggestionController.adapter
        binding.rvSearchSuggestion.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpObserver(){
        searchViewModel.suggestionLiveData.observe(viewLifecycleOwner){
            binding.rvSearchSuggestion.visibility = View.VISIBLE
            binding.rvSearchResult.visibility = View.GONE
            suggestionController.setData(it)
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
        binding.rvSearchSuggestion.visibility = View.GONE
        binding.rvSearchResult.visibility = View.VISIBLE
        hideKeyboard()
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

    private fun onSuggestionClick(query: String?){
        query?.let {
            search(it)
        }
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
        private const val SUGGESTION_NUMBER = 5
    }
}