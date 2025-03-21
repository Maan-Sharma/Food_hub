package com.example.food_hub.ui.feature.auth.signup

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food_hub.data.FoodApi
import com.example.food_hub.data.models.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpViewmodel @Inject constructor(val foodApi: FoodApi):ViewModel() {

    private val _uistate= MutableStateFlow<signupEvent>(signupEvent.Nothing)
    val Uistate=_uistate.asStateFlow()


    private val _navigationEvent= MutableSharedFlow<SignupNavigationEvent>()
    val navigationEvent=_navigationEvent.asSharedFlow()

    private val _email= MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _name = MutableStateFlow("")
    val name= _name.asStateFlow()

    private val _password = MutableStateFlow("")
    val password= _password.asStateFlow()

    fun onEmail(email: String){
        _email.value=email
    }
    fun onpasswordchange(password:String){
        _password.value=password
    }
    fun onNameChange(name:String){
        _name.value=name
    }


fun onSignUpClick(){

    viewModelScope.launch {

        _uistate.value=signupEvent.Loading
try {
    val response=   foodApi.signUp(
        SignUpRequest(
            name=name.value,
            email = email.value,
            password = password.value
        )
    )
    if(response.token.isNotEmpty()){
        _uistate.value=signupEvent.Success
        _navigationEvent.emit(SignupNavigationEvent.NavegationToHome)

    }

}catch (e:Exception){
    e.printStackTrace()
    _uistate.value=signupEvent.Error

}


    }

}

    fun onLogin() {
viewModelScope.launch {
    _navigationEvent.emit(SignupNavigationEvent.NavegationToLogin)
}
    }

    sealed class SignupNavigationEvent
    {
        object NavegationToHome: SignupNavigationEvent()
        object NavegationToLogin: SignupNavigationEvent()
    }

    sealed class signupEvent{
        object Nothing:signupEvent()
        object Success : signupEvent()
        object Error : signupEvent()
        object Loading: signupEvent()
    }


}