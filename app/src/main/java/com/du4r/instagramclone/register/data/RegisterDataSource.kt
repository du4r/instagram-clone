package com.du4r.instagramclone.register.data

import android.net.Uri

// interface que fornecera os metodos necessarios para implementar
// na classe que simulara nosso "servidor" e suas acoes no register usecase
// por hora possui apenas o metodo de criacao e sua sobrecarga.
interface RegisterDataSource {
    fun create(email: String,callback: RegisterCallBack)
    fun create(email: String, name: String, password: String, callback: RegisterCallBack)
    fun updateUser(photoUri:Uri,callback: RegisterCallBack)
}