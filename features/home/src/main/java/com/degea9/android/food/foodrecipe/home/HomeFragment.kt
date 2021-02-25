package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.degea9.android.food.foodrecipe.home.databinding.FragmentHomeBinding
import com.degea9.android.food.foodrecipe.model.CategoryRecipes
import com.degea9.android.foodrecipe.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val categoryRecipes: MutableList<CategoryRecipes> = mutableListOf()
    private val controller = HomeController(::onCategoryClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvRecipe.adapter = controller.adapter
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            homeViewModel.categoryRecipesList?.collectLatest {
                Timber.d("recipe name ${it.category} recipes size ${it.recipes.size}")
                categoryRecipes.add(CategoryRecipes(category = it.category, recipes = it.recipes))
                controller.setData(categoryRecipes)
            }
        }
    }

    private fun onCategoryClick(category: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryRecipesFragment(category))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}