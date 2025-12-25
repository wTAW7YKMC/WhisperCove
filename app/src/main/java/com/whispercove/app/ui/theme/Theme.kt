package com.whispercove.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// WhisperCove 核心色调：木屋+树洞+邮票，和谐复古
// 核心色调占比：背景主色70%，木屋主棕15%，树洞绿10%，邮票红5%
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8B6E4E), // 木屋主棕，温暖自然，占比15%
    secondary = Color(0xFF6B8E5D), // 树洞绿，辅助色，占比10%
    tertiary = Color(0xFFC83E37), // 邮票红，点睛色，占比5%
    background = Color(0xFFF9F6F3), // 纸白背景，呼应简历，占比70%
    surface = Color(0xFFF9F6F3), // 与背景相同，模拟纸质
    onPrimary = Color(0xFFF9F6F3), // 纸白色作为主色上的文字
    onSecondary = Color(0xFFF9F6F3),
    onTertiary = Color(0xFFF9F6F3),
    onBackground = Color(0xFF222222), // 文字主色，复用简历
    onSurface = Color(0xFF222222),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8B6E4E), // 木屋主棕，温暖自然，占比15%
    secondary = Color(0xFF6B8E5D), // 树洞绿，辅助色，占比10%
    tertiary = Color(0xFFC83E37), // 邮票红，点睛色，占比5%
    background = Color(0xFF222222), // 深色背景
    surface = Color(0xFF333333), // 深色表面
    onPrimary = Color(0xFFF9F6F3), // 纸白色作为主色上的文字
    onSecondary = Color(0xFFF9F6F3),
    onTertiary = Color(0xFFF9F6F3),
    onBackground = Color(0xFFF9F6F3), // 纸白色文字
    onSurface = Color(0xFFF9F6F3),
)

@Composable
fun WhisperCoveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}