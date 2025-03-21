package com.example.food_hub.ui.feature.auth.SingIn

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food_hub.data.FoodApi
import com.example.food_hub.data.models.SignUpRequest
import com.example.food_hub.data.models.SingInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewmodel @Inject constructor(val foodApi: FoodApi):ViewModel() {

    private val _uistate= MutableStateFlow<signInEvent>(signInEvent.Nothing)
    val Uistate=_uistate.asStateFlow()


    private val _navigationEvent= MutableSharedFlow<SignInNavigationEvent>()
    val navigationEvent=_navigationEvent.asSharedFlow()

    private val _email= MutableStateFlow("")
    val email = _email.asStateFlow()



    private val _password = MutableStateFlow("")
    val password= _password.asStateFlow()

    fun onEmail(email: String){
        _email.value=email
    }
    fun onpasswordchange(password:String){
        _password.value=password
    }



fun onSignInClick(){

    viewModelScope.launch {

        _uistate.value=signInEvent.Loading
try {
    val response=   foodApi.signIn(
        SingInRequest(
            email = email.value,
            password = password.value
        )
    )
    if(response.token.isNotEmpty()){
        _uistate.value=signInEvent.Success
        _navigationEvent.emit(SignInNavigationEvent.NavegationToHome)

    }

}catch (e:Exception){
    e.printStackTrace()
    _uistate.value=signInEvent.Error

}


    }

}

    fun onLogin() {
viewModelScope.launch {
    _navigationEvent.emit(SignInNavigationEvent.NavegationToLogin)
}
    }

    sealed class SignInNavigationEvent
    {
        object NavegationToHome: SignInNavigationEvent()
        object NavegationToLogin: SignInNavigationEvent()
    }

    sealed class signInEvent{
        object Nothing:signInEvent()
        object Success : signInEvent()
        object Error : signInEvent()
        object Loading: signInEvent()
    }


}