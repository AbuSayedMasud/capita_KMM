package com.leads.capita.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.leads.capitabull.android.R

val capitaFontFamily = FontFamily(
    Font(R.font.cabin_bold, FontWeight.Bold),
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_medium, FontWeight.Medium),

)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 15.5.sp
    ),
    caption = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )
    */
)

val typographySmall = Typography(
    body1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    body2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
    ),
    h3 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
    ),
    h2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
    ),
    h1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),

)

val typographyCompact = Typography(
    body1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    h6 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
    ),
    h5 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
    ),
    h4 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
    ),
    h3 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 29.sp,
    ),
    h2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
    ),
    h1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp,
    ),
)

val typographyMedium = Typography(
    body1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    body2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    h6 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    h5 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
    ),
    h4 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    h3 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
    ),
    h2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp,
    ),
    h1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 38.sp,
    ),
)

val typographyBig = Typography(
    body1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
    ),
    body2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 29.sp,
    ),
    h6 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
    ),
    h5 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
    ),
    h4 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 39.sp,
    ),
    h3 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp,
    ),
    h2 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
    ),
    h1 = TextStyle(
        fontFamily = capitaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
    ),
)
