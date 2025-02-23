package com.degea9.android.food.foodrecipe.scan

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.degea9.android.food.foodrecipe.scan.databinding.FragmentScanResultBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ScanResultFragment(override val coroutineContext: CoroutineContext= Dispatchers.Main) : BaseFragment(), CoroutineScope {

    private var dotNumber =1

    private lateinit var binding: FragmentScanResultBinding
    private var imageUri: Uri? = null
    private val viewModel: ScanViewModel by viewModels()

    private val scanResultController = ScanResultController(::onItemClick, ::onCategoryClick)

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUri = it.getParcelable("image_uri")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan_result, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
        imageUri?.let {
            viewModel.scanImage(it)
        }
    }

    private fun setup(){
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        scanResultController.spanCount = SPAN_COUNT
        binding.rvResult.setItemSpacingDp(16)
        layoutManager.spanSizeLookup = scanResultController.spanSizeLookup
        binding.rvResult.layoutManager = layoutManager
        binding.rvResult.adapter = scanResultController.adapter
    }

    private fun setupObserver(){
        viewModel.imageAnalysisLiveData.observe(viewLifecycleOwner, {
            handler.removeCallbacks(timeTask)
            binding.layoutLoading.visibility = View.GONE
            scanResultController.setData(it)
        })
    }
    private fun onItemClick(recipe: Recipe){
        recipe.id.let {
            findNavController().navigate(ScanResultFragmentDirections.actionScanResultFragmentToRecipeDetailFragment(it))
        }
    }

    private fun onCategoryClick(category: String?){
        category?.let {
            findNavController().navigate(ScanResultFragmentDirections.actionnScanResultFragmentToSearchFragment(it))
        }

    }

    override fun onResume() {
        super.onResume()
        analyzeLoading()
    }
    private fun analyzeLoading(){
        dotNumber = 1
        handler.post(timeTask)
    }

    private val timeTask = object : Runnable{
        override fun run() {
            if(dotNumber == 1){
                binding.tvLoadingDot.text ="."
                dotNumber++
                handler.postDelayed(this, 600)
            }else if(dotNumber == 2){
                binding.tvLoadingDot.text =".."
                dotNumber++
                handler.postDelayed(this, 600)
            }
            else if(dotNumber == 3){
                binding.tvLoadingDot.text ="..."
                dotNumber=1
                handler.postDelayed(this, 300)
            }
        }

    }

    override fun onPause() {
        handler.removeCallbacks(timeTask)
        super.onPause()
    }

    companion object {
        private const val SPAN_COUNT = 2
    }


}