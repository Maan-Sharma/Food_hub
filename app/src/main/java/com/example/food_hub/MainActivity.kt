package com.example.food_hub

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_hub.data.FoodApi
import com.example.food_hub.data.models.SignUpRequest
import com.example.food_hub.ui.feature.auth.AuthScreen
import com.example.food_hub.ui.feature.auth.SingIn.signInScreen
import com.example.food_hub.ui.feature.auth.signup.signUpScreen
import com.example.food_hub.ui.navigation.Auth
import com.example.food_hub.ui.navigation.Home
import com.example.food_hub.ui.navigation.Login
import com.example.food_hub.ui.navigation.Singup
import com.example.food_hub.ui.theme.Food_hubTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var foodApi: FoodApi
    var showSplashScreen by mutableStateOf(true) // ✅ Make it a state variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        installSplashScreen().apply {
            setKeepOnScreenCondition { showSplashScreen }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, 1f, 0f)
                val zoomY = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, 1f, 0f)

                zoomX.duration = 500
                zoomY.duration = 500
                zoomX.interpolator = OvershootInterpolator()
                zoomY.interpolator = OvershootInterpolator()

                zoomX.doOnEnd { screen.remove() } // ✅ Properly remove after animation
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }

        enableEdgeToEdge()
        setContent {
            Food_hubTheme {
                // ✅ Move delay logic inside Compose to trigger recomposition
                LaunchedEffect(Unit) {
                    delay(3000)
                    showSplashScreen = false
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val navController = rememberNavController()
                    NavHost(navController=navController, startDestination = Auth,
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            ) + fadeIn(animationSpec = tween(300))
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(300)
                            ) + fadeIn(animationSpec = tween(300))
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                        }
                    ){
                        composable<Singup> { signUpScreen(navController) }
                        composable<Auth> { AuthScreen(navController) }
                        composable<Login>(
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            }){
                            signInScreen(navController)
                        }
                        composable<Home> { Box(){} }
                    }
                }
            }
            if (::foodApi.isInitialized){
                Log.d("MainActivity","FoodApiInitialized")
            }
        }
    }
}


