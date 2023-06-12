package com.du4r.instagramclone.common.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.du4r.instagramclone.R
import com.du4r.instagramclone.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment: Fragment(R.layout.fragment_image_cropper) {
    private var binding : FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentImageCropperBinding.bind(view)

        //recolhe a uri da foto selecionada
        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding?.let {
            with(it){
                cropperContainer.setAspectRatio(1,1)
                cropperContainer.setFixedAspectRatio(true)
                cropperContainer.setImageUriAsync(uri)

                cropperBtnSave.setOnClickListener {
                    // aqui coletamos o caminho de um arquivo externo, nesse caso uma foto
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    // apos fazer a verificacao se o diretorio existe salvamos
                    // seu uri e o passamos para a ui
                    if (dir != null){
                        //criamos e salvamos o caminho de uma foto
                        val uriToSaved = Uri.fromFile(File(dir.path,System.currentTimeMillis().toString() + ".jpeg"))
                        cropperContainer.saveCroppedImageAsync(uriToSaved)
                    }
                }

                //apos salvar o caminho da imagem recebemos o callback
                // e coletamos o uri da imagem pelo argumento result
                cropperContainer.setOnCropImageCompleteListener{ view , result ->
                    // envia de volta o bundle para a RegisterPhotoFragment
                    // com a uri da imagem croppada
                    setFragmentResult("cropKey", bundleOf(KEY_URI to result.uri))

                    parentFragmentManager.popBackStack()
                }

                cropperBtnCancel.setOnClickListener {
                    // como se forcasse o botao de voltar
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_URI = "key_uri"
    }
}