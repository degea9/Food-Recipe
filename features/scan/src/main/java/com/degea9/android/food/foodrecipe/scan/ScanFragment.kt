package com.degea9.android.food.foodrecipe.scan

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.degea9.android.food.foodrecipe.scan.databinding.FragmentScanBinding
import com.degea9.android.foodrecipe.core.BaseFragment
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScanFragment : BaseFragment(), View.OnClickListener {
    private var imageCapture: ImageCapture? = null

    private lateinit var binding: FragmentScanBinding

    private var outputDirectory: File? = null
    private var cameraExecutor: ExecutorService?= null
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        binding.btnCameraCapture.setOnClickListener(this)
        binding.btnTryAgain.setOnClickListener(this)
        binding.btnAccept.setOnClickListener(this)

        setupCamera()
    }
    private fun setupCamera(){
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        startCamera()
    }
    private fun displayPreviewImage(imageUri: Uri) {
        showLayoutPreviewImage()
        Glide.with(this)
            .load(imageUri)
            .apply(RequestOptions.centerCropTransform())
            .into((binding.imgPreview))
    }

    private fun showLayoutCameraX() {
        binding.btnCameraCapture.isEnabled = true
        binding.viewCamera.isVisible = true
        binding.btnCameraCapture.isVisible = true
        binding.btnAccept.isVisible = false
        binding.btnTryAgain.isVisible = false
    }

    private fun showLayoutPreviewImage() {
        binding.viewCamera.isVisible = false
        binding.btnCameraCapture.isVisible = false
        binding.btnAccept.isVisible = true
        binding.btnTryAgain.isVisible = true
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewCamera.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Timber.e(exc, "Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, "Food Recipe").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_camera_capture -> takePhoto()
            R.id.btn_try_again -> showLayoutCameraX()
            R.id.btn_accept -> acceptImage()
        }
    }

    private fun takePhoto() {
        outputDirectory?.let {
            binding.btnCameraCapture.isEnabled = false
            // Get a stable reference of the modifiable image capture use case
            val imageCapture = imageCapture ?: return

            // Create time-stamped output file to hold the image
            val photoFile = File(
                    it,
                    SimpleDateFormat(
                            FILENAME_FORMAT, Locale.US
                    ).format(System.currentTimeMillis()) + ".jpg"
            )

            // Create output options object which contains file + metadata
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            // Set up image capture listener, which is triggered after photo has
            // been taken
            imageCapture.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(requireContext()),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exc: ImageCaptureException) {
                            Timber.e("Photo capture failed: ${exc.message}")
                        }

                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                            imageUri = Uri.fromFile(photoFile)
                            imageUri?.let { uri ->
                                displayPreviewImage(uri)
                            }
                        }
                    })
        }
    }

    private fun acceptImage() {
        imageUri?.let {
            findNavController().navigate(ScanFragmentDirections.actionScanFragmentToScanResult(it))
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.shutdown()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;

    }

    companion object {
        const val IMAGE_URI = "ImageUri"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}