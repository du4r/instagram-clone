package com.du4r.instagramclone.post.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.*
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.util.Files
import com.du4r.instagramclone.databinding.FragmentCameraBinding

class CameraFragment: Fragment() {
    private lateinit var binding: FragmentCameraBinding
    private lateinit var previewView: PreviewView
    private var imageCapture: ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        previewView = binding!!.cameraImg

        binding.cameraImgPicture.setOnClickListener{
            takePhoto()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("cameraKey"){ requestKey, bundle ->
            val shouldStart = bundle.getBoolean("startCamera")

            if (shouldStart){
                startCamera()
            }
        }
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageCapture = ImageCapture
                .Builder()
                .setTargetResolution(Size(480,480))
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,cameraSelector,preview,imageCapture)
            }catch (e: Exception){
                Log.e("teste", "failure initialize camera",e)
            }

        },ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return

        val photoFile = Files.generateFile(requireActivity())
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    setFragmentResult("takePhotoKey", bundleOf("uri" to savedUri))
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("teste","failure to take picture", exception)
                }
            })
    }


}