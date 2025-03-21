package com.example.food_hub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.food_hub.R


val poppinsFontFamily = FontFamily(
   Font(R.font.poppins_italic,FontWeight.Normal),
   Font(R.font.poppins_medium,FontWeight.Medium),
   Font(R.font.poppins_bold,FontWeight.Bold),
   Font(R.font.poppins_thin,FontWeight.Thin),
   Font(R.font.poppins_regular,FontWeight.Normal),
   Font(R.font.poppins_black,FontWeight.Black),
   Font(R.font.poppins_light,FontWeight.Light),
   Font(R.font.poppins_medium,FontWeight.Medium),
   Font(R.font.poppins_semibold,FontWeight.SemiBold),
   Font(R.font.poppins_blackitalic,FontWeight.Light),
   Font(R.font.poppins_exralight,FontWeight.Normal),
   Font(R.font.poppins_extrabolditalic,FontWeight.Normal),
   Font(R.font.poppins_extralightitalic,FontWeight.Normal),
   Font(R.font.poppins_mediumitalic,FontWeight.Normal),
   Font(R.font.poppins_semibolditalic,FontWeight.Normal),
   Font(R.font.poppins_thinitalic,FontWeight.Normal),
   Font(R.font.poppins_lightitalic,FontWeight.Normal),
   Font(R.font.poppins_bolditalic,FontWeight.Normal),

)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp


    )

)