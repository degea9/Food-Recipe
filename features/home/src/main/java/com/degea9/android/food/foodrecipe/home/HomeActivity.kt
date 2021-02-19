package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.degea9.android.foodrecipe.core.BaseActivity
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupObserver()
    }

    private fun setupObserver(){
        homeViewModel.popularRecipes.observe(this, Observer {
            Timber.d("popularRecipes size ${it.size}")
            setupRecyclerView(it)
        })
    }

    private fun setupRecyclerView(recipe:List<Recipe>) {
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
                    }

            }

            // Carousel
            // This extension function come with epoxy
            carousel {
                id("recipe")
                paddingRes(R.dimen.material_helper_text_font_1_3_padding_top)
                padding(Carousel.Padding(30,30))
                Carousel.setDefaultGlobalSnapHelperFactory(null)
                models(carouselItemModels)
            }
        }
    }
}