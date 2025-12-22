package com.whispercove.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 印泥红 - 新增点睛色，模拟信件印泥/邮票红
val SealRed = Color(0xFFC83E37)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF222222), // 墨黑 - 完全复用简历主色
    secondary = SealRed, // 印泥红作为次要色
    tertiary = Color(0xFFE0E0E0), // 浅灰 - 复用简历浅灰
    background = Color(0xFF222222), // 深色背景
    surface = Color(0xFF333333), // 深色表面
    onPrimary = Color(0xFFF9F6F3), // 纸白色作为主色上的文字
    onSecondary = Color(0xFFF9F6F3),
    onTertiary = Color(0xFF222222),
    onBackground = Color(0xFFF9F6F3), // 纸白色文字
    onSurface = Color(0xFFF9F6F3),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF222222), // 墨黑 - 完全复用简历主色
    secondary = SealRed, // 印泥红作为次要色
    tertiary = Color(0xFFE0E0E0), // 浅灰 - 复用简历浅灰
    background = Color(0xFFF9F6F3), // 纸白底色 - 复刻简历纸白底色
    surface = Color(0xFFF9F6F3), // 与背景相同，模拟纸质
    onPrimary = Color(0xFFF9F6F3), // 纸白色作为主色上的文字
    onSecondary = Color(0xFFF9F6F3),
    onTertiary = Color(0xFF222222),
    onBackground = Color(0xFF222222), // 墨黑文字
    onSurface = Color(0xFF222222),
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