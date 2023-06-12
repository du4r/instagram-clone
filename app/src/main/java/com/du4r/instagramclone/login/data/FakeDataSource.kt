package com.du4r.instagramclone.login.data

import android.os.Handler
import android.os.Looper
import com.du4r.instagramclone.common.model.DataBase

class FakeDataSource: LoginDataSource {

    override fun login(email: String, password: String, callBack: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull{email == it.email}

            when{
                userAuth == null -> {
                    callBack.onFailure("Usuario nao encontrado")
                }
                userAuth.password != password -> {
                    callBack.onFailure("Senha incorreta")
                }
                else -> {
                    DataBase.sessionAuth = userAuth
                    callBack.onSuccess()
                }
            }

            callBack.onComplete()
        }, 2000)
    }

}