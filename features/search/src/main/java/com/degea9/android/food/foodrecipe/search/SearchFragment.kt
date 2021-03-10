package com.degea9.android.food.foodrecipe.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.degea9.android.food.foodrecipe.search.databinding.FragmentSearchBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SearchFragment(override val coroutineContext: CoroutineContext = Dispatchers.Main) : BaseFragment(), CoroutineScope  {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    //controller for epoxy recyclerview
    private val pagingController = SearchResultPagingController(::onItemClick)

    private val suggestionController = SuggestionController(::onSuggestionClick, ::onSeeAllClick)

    private val historyController = SearchHistoryController(::onItemClick, ::onSuggestionClick)

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
                    if(searchText.isNotEmpty()){
                        binding.rvSearchHistory.visibility = View.GONE
                        binding.rvSearchResult.visibility = View.VISIBLE
                        binding.rvSearchSuggestion.visibility = View.VISIBLE
                        searchViewModel.getSuggestKeyword(s.toString(), SUGGESTION_NUMBER)
                    }
                    else {
                        searchViewModel.getSearchHistory()
                        binding.rvSearchResult.visibility = View.GONE
                        binding.rvSearchSuggestion.visibility = View.GONE
                        binding.rvSearchHistory.visibility = View.VISIBLE
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }
        binding.edtSearch.addTextChangedListener(watcher)
    }
    private fun setup(){
        setupEditTextSearch()
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

        binding.rvSearchHistory.adapter = historyController.adapter
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpObserver(){
        searchViewModel.suggestionLiveData.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.rvSearchSuggestion.visibility = View.GONE

            }
            else {
                binding.rvSearchSuggestion.visibility = View.VISIBLE
            }
            binding.rvSearchResult.visibility = View.GONE
            suggestionController.setData(it)
        }

        searchViewModel.searchHistoryLiveData.observe(viewLifecycleOwner){
            Timber.d("SearchHistory: ${it}")
            binding.rvSearchHistory.visibility = View.VISIBLE
            binding.rvSearchResult.visibility = View.GONE
            binding.rvSearchSuggestion.visibility =View.GONE
            historyController.setData(it)
        }
        searchViewModel.getSearchHistory()
    }

    private fun updateRepoListFromInput() {
        binding.edtSearch.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun search(query: String) {
        if(query.isNotEmpty()){
            searchViewModel.saveSuggestionKeyword(SuggestionKeyword(null, query, null))
        }
        binding.rvSearchHistory.visibility= View.GONE
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
            Timber.d("ADDHISTORY: onItemClick")
            searchViewModel.addHistoryRecipe(it)
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

    private fun onSeeAllClick(){
        search(binding.edtSearch.text.toString())
    }

    override fun onResume() {
        super.onResume()
        binding.edtSearch.requestFocus()
        showKeyboard()
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
        private const val SUGGESTION_NUMBER = 5
    }
}