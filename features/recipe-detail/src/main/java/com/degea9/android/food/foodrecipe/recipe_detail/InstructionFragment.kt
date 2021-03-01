package com.degea9.android.food.foodrecipe.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.degea9.android.food.foodrecipe.recipe_detail.databinding.FragmentRecipeDetailBinding
import com.degea9.android.food.foodrecipe.recipe_detail.databinding.FragmentRecipeInstructionBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RecipeInstructionFragment : Fragment() {

    private lateinit var binding:FragmentRecipeInstructionBinding

    private val instructionController: InstructionController = InstructionController()

    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }

    private fun setup(){
        binding.rvInstructions.adapter = instructionController.adapter
    }

    private fun setupObserver(){
        recipeDetailViewModel.recipeDetailLiveData?.observe(viewLifecycleOwner) {
            Timber.d("recipe instruction ${it.analyzedInstructions?.getOrNull(0)}")
            instructionController.setData(it.analyzedInstructions?.getOrNull(0))
        }
    }
}