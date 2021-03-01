package com.degea9.android.food.foodrecipe.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.degea9.android.food.foodrecipe.recipe_detail.databinding.FragmentIngredientBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class IngredientFragment : Fragment() {

    private lateinit var binding: FragmentIngredientBinding
    private val ingredientController = IngredientController()
    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIngredientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }

    private fun setup(){
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        ingredientController.spanCount = SPAN_COUNT
        layoutManager.spanSizeLookup = ingredientController.spanSizeLookup
        binding.rvIngredient.layoutManager = layoutManager
        binding.rvIngredient.adapter = ingredientController.adapter
    }
    private fun setupObserver(){
        recipeDetailViewModel.recipeDetailLiveData?.observe(viewLifecycleOwner) {
            Timber.d("recipe instruction ${it.analyzedInstructions?.getOrNull(0)}")
            ingredientController.setData(it.extendedIngredients.orEmpty())
        }
    }

    companion object {
        private const val SPAN_COUNT = 3
    }
}