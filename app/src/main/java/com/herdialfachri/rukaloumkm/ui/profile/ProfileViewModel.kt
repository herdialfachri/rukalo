package com.herdialfachri.rukaloumkm.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isUserLoggedIn = MutableLiveData<Boolean>().apply {
        value = firebaseAuth.currentUser != null
    }
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    private val _logoutEvent = MutableLiveData<Unit>()
    val logoutEvent: LiveData<Unit> = _logoutEvent

    fun logout() {
        firebaseAuth.signOut()
        _logoutEvent.value = Unit
    }

    fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
