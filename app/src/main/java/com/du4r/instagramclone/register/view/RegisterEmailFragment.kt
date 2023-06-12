package com.du4r.instagramclone.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.util.TxtWatcher
import com.du4r.instagramclone.databinding.FragmentRegisterEmailBinding
import com.du4r.instagramclone.register.RegisterEmail
import com.du4r.instagramclone.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment: Fragment(R.layout.fragment_register_email),RegisterEmail.View {

    override lateinit var presenter: RegisterEmail.Presenter
    private var binding: FragmentRegisterEmailBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding?.let {
            with(it){
                //retorna a main activity ao clicar em "faca login"
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerBtnNext.setOnClickListener{
                    presenter.create(registerEditEmail.text.toString())
                }

                //verifica se o texto do formulario mudou para entao ativar
                //ou desativar o botao de proximo
                registerEditEmail.addTextChangedListener(watcher)
                //verifica o email
                registerEditEmail.addTextChangedListener(TxtWatcher{
                    displayEmailFailure(null)
                })
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    private val watcher = TxtWatcher {
        binding?.registerBtnNext?.isEnabled =
            binding?.registerEditEmail?.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEditEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String?) {
       binding?.registerEditEmailInput?.error = message
    }

    override fun goToNameAndPasswordScreen(email: String) {
        fragmentAttachListener?.goToRegisterNamePasswordScreen(email)
    }

    override fun onDestroy() {
        fragmentAttachListener = null
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}