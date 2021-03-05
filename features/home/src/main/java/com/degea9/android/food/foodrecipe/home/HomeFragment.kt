package com.degea9.android.food.foodrecipe.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.degea9.android.food.foodrecipe.home.databinding.FragmentHomeBinding
import com.degea9.android.food.foodrecipe.model.CategoryRecipes
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val categoryRecipes: MutableList<CategoryRecipes> = mutableListOf()
    private val controller = HomeController(::onCategoryClick, ::onItemClick, ::onLikeClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvRecipe.adapter = controller.adapter
        categoryRecipes.clear()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        setup()
        setupObserver()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setup() {
        binding.edtSearch.setOnTouchListener { _, _ ->
            mayNavigate()
            return@setOnTouchListener false
        }
    }

    private fun Fragment.mayNavigate(): Boolean {

        val navController = findNavController()
        val destinationIdInNavController = navController.currentDestination?.id

        // add tag_navigation_destination_id to your ids.xml so that it's unique:
        val destinationIdOfThisFragment = view?.getTag(R.id.homeFragment) ?: destinationIdInNavController

        // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
        if (destinationIdInNavController == destinationIdOfThisFragment) {
            view?.setTag(R.id.homeFragment, destinationIdOfThisFragment)
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            return true
        } else {
            Timber.d("HomeFragment: May not navigate: current destination is not the current fragment.")
            return false
        }
    }
    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.categoryRecipesList?.collectLatest {
                Timber.d("recipe name ${it.category} recipes size ${it.recipes.size}")
                categoryRecipes.add(it)
                controller.setData(categoryRecipes)
            }
        }
    }

    private fun onCategoryClick(category: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToCategoryRecipesFragment(
                category
            )
        )
    }

    private fun onItemClick(recipe: Recipe) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(
                recipe.id
            )
        )
    }

    private fun onLikeClick(recipe: Recipe) {
        homeViewModel.addFavoriteRecipe(recipe)
    }
}