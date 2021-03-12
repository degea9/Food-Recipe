package com.degea9.android.food.foodrecipe.scan

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.degea9.android.food.foodrecipe.scan.databinding.FragmentScanResultBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ScanResultFragment(override val coroutineContext: CoroutineContext= Dispatchers.Main) : BaseFragment(), CoroutineScope {

    private lateinit var binding: FragmentScanResultBinding
    private var imageUri: Uri? = null
    private val viewModel: ScanViewModel by viewModels()

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
        setupObserver()
        imageUri?.let {
            viewModel.scanImage(it)
        }
    }

    private fun setupObserver(){
        viewModel.imageAnalysisLiveData.observe(viewLifecycleOwner, {
            Log.d("AAAAAAAA", it.toString())
        })
    }


}