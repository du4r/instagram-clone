package com.du4r.instagramclone.login

import androidx.annotation.StringRes
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

//aqui implementamos uma interface para o usecase login, onde ha
//uma interface para o presenter e uma outra para a view
//aplicando o padrao mvp
interface Login {

    //a interface do presenter possui apenas a funcao login,
    // que implementa uma outra interface basepresenter hendando
    // o metodo onDestroy()
    // o presenter ira efetuar a operacao do login mais a frente
    interface Presenter: BasePresenter{
        fun login(email: String, password: String)
    }
    // quanto a view implementa uma outra interface baseview
    // que recebe um presenter no seu construtor para mais a frente fazendo uma ligacao entre os dois
    // essa view possui o metodo de mostrar o progress button, outro de validar o email e um para a senha
    // alem de notificar quando o usuario foi autenticado ou se o login deu erro
    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }

}