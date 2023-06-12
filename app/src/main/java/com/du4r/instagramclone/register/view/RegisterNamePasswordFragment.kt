package com.du4r.instagramclone.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.util.TxtWatcher
import com.du4r.instagramclone.databinding.FragmentNamePasswordBinding
import com.du4r.instagramclone.register.RegisterNameAndPassword
import com.du4r.instagramclone.register.presentation.RegisterNameAndPasswordPresenter

class RegisterNamePasswordFragment: Fragment(R.layout.fragment_name_password),
RegisterNameAndPassword.View{
    override lateinit var presenter: RegisterNameAndPassword.Presenter
    private  var fragmentAttachListener: FragmentAttachListener? = null
    private var binding : FragmentNamePasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNamePasswordBinding.bind(view)

        presenter = RegisterNameAndPasswordPresenter(this, DependencyInjector.registerEmailRepository())

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("email not found")

        binding?.let {
            with(it){
                registerTxtLogin.addTextChangedListener(TxtWatcher{
                    activity?.finish()
                })

                registerNameBtnNext.setOnClickListener{
                    presenter.create(
                        email,
                        registerEditName.text.toString(),
                        registerEditPassword.text.toString(),
                        registerEditConfirmPassword.text.toString()
                    )
                }

                registerEditName.addTextChangedListener (watcher)
                registerEditPassword.addTextChangedListener(watcher)
                registerEditConfirmPassword.addTextChangedListener(watcher)

                registerEditName.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })

                registerEditPassword.addTextChangedListener(TxtWatcher{
                    displayPasswordFailure(null)
                })

                registerEditConfirmPassword.addTextChangedListener(TxtWatcher{
                    displayPasswordFailure(null)
                })
            }
        }


        Log.i("teste", email.toString())
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
       binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passError: Int?) {
        binding?.registerEditPasswordInput?.error = passError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG)
    }

    override fun onCreateSuccess(name: String) {
        fragmentAttachListener?.goToWelcomeScreen(name)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object{
    const val KEY_EMAIL = "key_email"
    }

    private val watcher = TxtWatcher{
        binding?.registerNameBtnNext?.isEnabled = binding?.registerEditName?.text.toString().isNotEmpty()
                && binding?.registerEditPassword?.text.toString().isNotEmpty()
                && binding?.registerEditConfirmPassword?.text.toString().isNotEmpty()
    }
}