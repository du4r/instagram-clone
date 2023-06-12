package com.du4r.instagramclone.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.UserAuth
import java.util.UUID

// classe que implementa uma fonte de dados simulando um "servidor"
// para realizar alguns testes no app

class FakeRegisterDataSource: RegisterDataSource {

    override fun create(email: String, callback: RegisterCallBack) {

        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = DataBase.usersAuth.firstOrNull{email == it.email}

            if (userAuth == null){
                callback.onSuccess()
            }else{
                callback.onFailure("Usuario ja cadastrado")
            }

            callback.onComplete()
        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = DataBase.usersAuth.firstOrNull{email == it.email}

            // verificamos se o usuario ja existe
            // caso nao exista, criamos um novo, armazenamos no Database.sessionAuth
            // por fim mandamos um callback para o repository
            if (userAuth != null){
                callback.onFailure("Usuario ja cadastrado")
            }else{
                val newUser = UserAuth(UUID.randomUUID().toString(),name,email,password, null)
                val created = DataBase.usersAuth.add(newUser)
                if(created){
                    DataBase.sessionAuth = newUser

                    DataBase.followers[newUser.UUID] = hashSetOf()
                    DataBase.feeds[newUser.UUID] = hashSetOf()
                    DataBase.posts[newUser.UUID] = hashSetOf()

                    callback.onSuccess()
                }else{
                    //caso o usuario nao for criado por algum erro inesperado o callback sera o de falha
                    callback.onFailure("Erro interno do servidor")
                }
            }

            callback.onComplete()
        }, 2000)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = DataBase.sessionAuth

            if (userAuth == null){
                callback.onFailure("Usuario ja cadastrado")
            }else{
                val index = DataBase.usersAuth.indexOf(DataBase.sessionAuth)
                DataBase.usersAuth[index] = DataBase.sessionAuth!!.copy(photoUri = photoUri)
                DataBase.sessionAuth = DataBase.usersAuth[index]

                callback.onSuccess()
            }

            callback.onComplete()
        }, 2000)
    }
}