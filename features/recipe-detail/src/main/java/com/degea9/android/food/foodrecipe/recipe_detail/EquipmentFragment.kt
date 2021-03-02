package com.degea9.android.food.foodrecipe.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.degea9.android.food.foodrecipe.recipe_detail.databinding.FragmentEquipmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EquipmentFragment : Fragment() {

    private lateinit var binding: FragmentEquipmentBinding
    private val equipmentController = EquipmentController()
    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels(
        ownerProducer = {requireParentFragment()}
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_equipment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }
    private fun setup(){
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        equipmentController.spanCount = SPAN_COUNT
        layoutManager.spanSizeLookup = equipmentController.spanSizeLookup
        binding.rvEquipment.layoutManager = layoutManager
        binding.rvEquipment.adapter = equipmentController.adapter
    }

    private fun setupObserver(){
        recipeDetailViewModel.recipeDetailLiveData?.observe(viewLifecycleOwner){
            Timber.d("recipe equipment ${it.analyzedInstructions?.getOrNull(0)}")
            equipmentController.setData(it.analyzedInstructions?.getOrNull(0))
        }
    }

    companion object {
        private const val SPAN_COUNT = 3
    }

}