package com.example.food_hub.ui.feature.auth.SingIn

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.food_hub.R
import com.example.food_hub.ui.GroupSocalButton
import com.example.food_hub.ui.feature.auth.AuthScreen
import com.example.food_hub.ui.fooodhubTextfield
import com.example.food_hub.ui.navigation.Auth
import com.example.food_hub.ui.navigation.Home
import com.example.food_hub.ui.navigation.Login
import com.example.food_hub.ui.theme.orange
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun signInScreen(navController: NavController,viewModel:SingInViewmodel= hiltViewModel()){
Box(modifier=Modifier.fillMaxSize()){

    val email =viewModel.email.collectAsStateWithLifecycle()
    val password =viewModel.password.collectAsStateWithLifecycle()
    val errormassage = remember { mutableStateOf<String?>(null) }
    val loading =  remember { mutableStateOf(false) }


    val uiState=viewModel.Uistate.collectAsState()
    when(uiState.value){

        is SingInViewmodel.signInEvent.Error ->{
               errormassage.value="Failed"
            loading.value=false
        }
        is SingInViewmodel.signInEvent.Loading->{
            errormassage.value= null
            loading.value=true

        }
        else ->{
            //do nothing

            loading.value=false
            errormassage.value=null
        }
    }
    val context = LocalContext.current

    LaunchedEffect(true){
        viewModel.navigationEvent.collectLatest {event->
            when(event){
                is SingInViewmodel.SignInNavigationEvent.NavegationToHome ->{
                    navController.navigate(Home) {
                        popUpTo(Auth) {
                            inclusive = true
                        }
                    }
                }
                is SingInViewmodel.SignInNavigationEvent.NavegationToLogin ->{
                    navController.navigate(Login)
                }
              
            }
        }
    }


    Image(painter = painterResource(R.drawable.ic_auth_bg), contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

    Box(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.sign_In),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))

        fooodhubTextfield(
            value = email.value,
            onValueChange = {viewModel.onEmail(it)},
            label = {
                Text(text = stringResource(id = R.string.email),color=Color.Gray)
            },
            modifier = Modifier.fillMaxWidth(),
        )

        fooodhubTextfield(
            value = password.value,
            onValueChange = {viewModel.onpasswordchange(it)},
            label = {
                Text(text = stringResource(id = R.string.password),color=Color.Gray)
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        Spacer(modifier = Modifier.size(16.dp))

        Text(text = errormassage.value ?:"",color=Color.Red)

        Button(
            onClick = viewModel::onSignInClick, modifier = Modifier.height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orange)
        ) {
            Box {
                AnimatedContent(targetState = loading.value,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f) togetherWith
                                fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                    }
                ) { target ->
                    if (target) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .size(24.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.sign_In),
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                    }

                }


            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    Text(text = stringResource(R.string.dont_have_a_account),
        modifier = Modifier.
    padding(8.dp)
            .clickable {
                viewModel.onLogin()
            }
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
        GroupSocalButton(onFaceBookClick = {}, color = Color.Black) { }
    }
}
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
signInScreen(rememberNavController())
}