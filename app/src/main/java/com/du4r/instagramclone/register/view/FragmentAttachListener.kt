package com.du4r.instagramclone.register.view

interface FragmentAttachListener {
    fun goToRegisterNamePasswordScreen(email: String?)
    fun goToWelcomeScreen(name: String)
    fun goToPhotoScreen()
    fun goToMainScreen()
    fun goToGalleryScreen()
    fun goToCameraScreen()
}