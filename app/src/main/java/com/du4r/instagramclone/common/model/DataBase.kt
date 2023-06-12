package com.du4r.instagramclone.common.model

import android.net.Uri
import com.du4r.instagramclone.common.model.DataBase.followers
import java.io.File
import java.util.*
import kotlin.collections.HashMap

object DataBase {
    val usersAuth = mutableListOf<UserAuth>()
    val posts = HashMap<String,MutableSet<Post>>()
    val feeds = HashMap<String,MutableSet<Post>>()
    val followers = hashMapOf<String, MutableSet<String>>()

    var sessionAuth: UserAuth? = null


    init {
        val userA = UserAuth(UUID.randomUUID().toString(),"UserA","userA@gmail.com","12345678",
            Uri.fromFile(File("")))
        val userB = UserAuth(UUID.randomUUID().toString(),"UserB","userB@gmail.com","12345678a",
        Uri.fromFile(File("/storage/self/primary/Android/media/com.du4r.instagramclone/InstaClone/2023-05-14-17-56-24-928.jpg")))

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.UUID] = hashSetOf()
        posts[userA.UUID] = hashSetOf()
        feeds[userA.UUID] = hashSetOf()

        followers[userB.UUID] = hashSetOf()
        posts[userB.UUID] = hashSetOf()
        feeds[userB.UUID] = hashSetOf()

        for (i in 0..30){
            val user = UserAuth(UUID.randomUUID().toString(),
                "User $i","user$i@gmail.com","121121121", null)
            usersAuth.add(user)
        }

        sessionAuth = usersAuth.first()

        followers[sessionAuth!!.UUID]?.add(usersAuth[2].UUID)
    }
}