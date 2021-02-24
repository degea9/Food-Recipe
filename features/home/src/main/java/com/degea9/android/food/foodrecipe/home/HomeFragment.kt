package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.degea9.android.food.foodrecipe.home.databinding.FragmentHomeBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        homeViewModel.popularRecipes.observe(viewLifecycleOwner, Observer {
            Timber.d("popularRecipes size ${it.size}")
            setupRecyclerView(it)
        })
    }

    private fun setupRecyclerView(recipe: List<Recipe>) {
        rvRecipe.withModels {
            title {
                id("title-id")
                title("Need Assistant")
            }
            // Carousel Item
            val carouselItemModels = recipe.map { currentItem ->
                PopularRecipeBindingModel_()
                    .id(currentItem.id)
                    .recipePopularItem(currentItem)
                    .clickListener { v ->
                        Timber.d("Click to ${currentItem.title}")
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryRecipesFragment())
                    }

            }

            // Carousel
            // This extension function come with epoxy
            carousel {
                id("recipe")
                padding(Carousel.Padding(30, 30))
                Carousel.setDefaultGlobalSnapHelperFactory(null)
                models(carouselItemModels)
            }
        }
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