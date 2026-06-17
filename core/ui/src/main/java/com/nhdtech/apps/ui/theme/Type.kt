package com.nhdtech.apps.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nhdtech.apps.ui.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val AppTypography = Typography(

    displayLarge = Typography().displayLarge.copy(
        fontFamily = InterFontFamily
    ),

    displayMedium = Typography().displayMedium.copy(
        fontFamily = InterFontFamily
    ),

    displaySmall = Typography().displaySmall.copy(
        fontFamily = InterFontFamily
    ),

    headlineLarge = Typography().headlineLarge.copy(
        fontFamily = InterFontFamily
    ),

    headlineMedium = Typography().headlineMedium.copy(
        fontFamily = InterFontFamily
    ),

    headlineSmall = Typography().headlineSmall.copy(
        fontFamily = InterFontFamily
    ),

    titleLarge = Typography().titleLarge.copy(
        fontFamily = InterFontFamily
    ),

    titleMedium = Typography().titleMedium.copy(
        fontFamily = InterFontFamily
    ),

    titleSmall = Typography().titleSmall.copy(
        fontFamily = InterFontFamily
    ),

    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = InterFontFamily
    ),

    bodyMedium = Typography().bodyMedium.copy(
        fontFamily = InterFontFamily
    ),

    bodySmall = Typography().bodySmall.copy(
        fontFamily = InterFontFamily
    ),

    labelLarge = Typography().labelLarge.copy(
        fontFamily = InterFontFamily
    ),

    labelMedium = Typography().labelMedium.copy(
        fontFamily = InterFontFamily
    ),

    labelSmall = Typography().labelSmall.copy(
        fontFamily = InterFontFamily
    )
)