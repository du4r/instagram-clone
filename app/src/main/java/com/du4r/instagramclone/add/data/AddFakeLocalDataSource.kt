package com.du4r.instagramclone.add.data

import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.UserAuth

class AddFakeLocalDataSource: AddDataSource {
    override fun fetchSession(): UserAuth {
        return DataBase.sessionAuth ?: throw RuntimeException("usuario nao logado!!")
    }

}