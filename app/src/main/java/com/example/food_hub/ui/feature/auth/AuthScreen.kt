package com.example.food_hub.ui.feature.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.food_hub.R
import com.example.food_hub.ui.GroupSocalButton
import com.example.food_hub.ui.navigation.Login
import com.example.food_hub.ui.navigation.Singup
import com.example.food_hub.ui.theme.orange

@Composable
fun AuthScreen(navController: NavController){
    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }
    val brush = Brush.verticalGradient(
        colors = listOf(
            androidx.compose.ui.graphics.Color.Transparent,
            androidx.compose.ui.graphics.Color.Black
        ),
        startY = imageSize.value.height.toFloat()/3,
    )
   Box(modifier=Modifier.fillMaxSize().background(Color.Black)){
       Image(painter = painterResource(id = R.drawable.welcome), contentDescription = null,
           modifier = Modifier.onGloballyPositioned {
               imageSize.value=it.size
           }.alpha(0.8f),
           contentScale = ContentScale.FillBounds)
       Box(modifier = Modifier.matchParentSize().background(brush = brush))

       Button(
           onClick = { "Authbe" },
           colors = ButtonDefaults.buttonColors(
               containerColor = Color.White, // Set button background color
               contentColor = Color.Black    // Set text color inside the button
           ),
           modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
       ) {
           Text("Skip", color = orange)
       }
       Column(modifier = Modifier.fillMaxWidth().padding(top=110.dp).padding(16.dp)) {
           Text(
               text = stringResource(id = R.string.welcome),
               color = Color.Black,
               modifier = Modifier,
               fontSize = 50.sp,
               fontWeight = FontWeight.Bold

           )
           Text(
               text = stringResource(id = R.string.app_name),
               color = orange,
               modifier = Modifier,
               fontSize = 50.sp,
               fontWeight = FontWeight.Bold


           )
           Text(
               text = stringResource(id=R.string.fudhubdec),
               color = Color.DarkGray,
               fontSize = 16.sp,
               modifier=Modifier.padding(vertical = 16.dp)
           )


       }
       // Social Login Buttons
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .align(Alignment.BottomCenter)
               .padding(16.dp),
           horizontalAlignment = Alignment.CenterHorizontally

       ) {
          GroupSocalButton(onFaceBookClick = {}) {

          }
Spacer(modifier = Modifier.size(16.dp))
           // Sign in with Email Button
           Button(
               onClick = {navController.navigate(Singup)},
               modifier = Modifier.fillMaxWidth(),
               colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
               border = BorderStroke(1.dp, color = Color.White)
           ) {
               Text(text = stringResource(id = R.string.sign_with_email), color = Color.White)
           }

           // Already have an account? Login
           TextButton(onClick = { navController.navigate(Login) }) {
               Text(
                   text = stringResource(id = R.string.already_login),
                   color = Color.White
               )
           }
       }
   }
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen(rememberNavController())
}