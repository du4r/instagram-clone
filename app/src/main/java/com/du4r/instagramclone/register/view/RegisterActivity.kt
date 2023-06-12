package com.du4r.instagramclone.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.extension.hideKeyboard
import com.du4r.instagramclone.common.extension.replaceFragment
import com.du4r.instagramclone.common.view.CropperImageFragment
import com.du4r.instagramclone.common.view.CropperImageFragment.Companion.KEY_URI
import com.du4r.instagramclone.databinding.ActivityRegisterBinding
import com.du4r.instagramclone.main.view.MainActivity
import com.du4r.instagramclone.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.du4r.instagramclone.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { openImageCropper(it) }
        }

    private val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
        if (saved) {
            openImageCropper(currentPhoto)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //pega a referencia do fragmento
        val fragment = RegisterEmailFragment()

        //injeta o fragmento no frameLayout
        replaceFragment(fragment)

    }

    override fun goToRegisterNamePasswordScreen(email: String?) {

        val fragment = RegisterNamePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EMAIL, email)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val fragment = RegisterWelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }


    override fun goToCameraScreen() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {

            val photoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Log.e("RegisterActivity", e.message, e)
                null
            }

            photoFile?.also {
                val photoUri =
                    FileProvider.getUriForFile(this, "com.du4r.instagramclone.fileProvider", it)
                currentPhoto = photoUri

                getCamera.launch(photoUri)
            }
        }
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun goToGalleryScreen() {
        getContent.launch("image/*")
    }

    // gera um arquivo temporario
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat(
            "yyyyMMdd_HHmmss",
            Locale.getDefault()
        ).format(Date())
        // vai armazenar no diretorio unico do app para a foto
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.register_fragment,fragment)
        hideKeyboard()
    }

    private fun openImageCropper(uri: Uri) {
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }
}