package com.degea9.android.food.foodrecipe.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.degea9.android.food.foodrecipe.category_recipes.ui.CategoryRecipesFragmentArgs
import com.degea9.android.food.foodrecipe.recipe_detail.databinding.FragmentRecipeDetailBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailFragment : BaseFragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var recipeDetailViewModelFactory: RecipeDetailViewModel.AssistedFactory

    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels{
        RecipeDetailViewModel.provideFactory(recipeDetailViewModelFactory,args.recipeID)
    }

    private lateinit var binding:FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }

    private fun setup(){
        val pagerAdapter = RecipeDetailViewPagerAdapter(this)
        binding.viewpager.adapter = pagerAdapter
        // Set the icon and text for each tab
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position:Int):String{
        return when (position) {
            INSTRUCTION_PAGE_INDEX -> getString(R.string.instruction)
            INGREDIENT_PAGE_INDEX -> getString(R.string.ingredient)
            EQUIPMENT_PAGE_INDEX -> getString(R.string.equipment)
            else -> ""
        }
    }

    private fun setupObserver(){
        recipeDetailViewModel.recipeDetailLiveData?.observe(viewLifecycleOwner){
            binding.recipe = it
        }
    }

}