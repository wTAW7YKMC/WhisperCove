package com.whispercove.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// 复古印刷风格字体，模拟信件印刷感
// 注意：实际项目中应添加Special Elite和Patrick Hand字体文件
// 这里使用系统字体作为替代，实际开发时需下载并添加Google Fonts
val Typography = Typography(
    // 页面标题 - 22sp - Special Elite字体（复古印刷体）
    titleLarge = TextStyle(
        fontFamily = FontFamily.Monospace, // 替代Special Elite，实际项目中应替换
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 24.2.sp, // 1.1倍行间距，复刻印刷品紧凑感
        letterSpacing = 0.sp
    ),
    // 正文内容（盲盒）- 16sp - Special Elite字体（印刷体信件）
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace, // 替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.2.sp, // 1.2倍行间距
        letterSpacing = 0.sp
    ),
    // 按钮/标签文字 - 14sp - Special Elite字体
    labelLarge = TextStyle(
        fontFamily = FontFamily.Monospace, // 替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.8.sp, // 1.2倍行间距
        letterSpacing = 0.sp
    ),
    // 辅助文字 - 12sp - 思源黑体
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default, // 替代思源黑体
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.4.sp, // 1.2倍行间距
        letterSpacing = 0.sp
    ),
    // 回应内容（手写回信）- 16sp - Patrick Hand字体
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Cursive, // 替代Patrick Hand，模拟手写体
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.2.sp, // 1.2倍行间距
        letterSpacing = 0.5.sp // 手写体稍宽字间距
    ),
    // 模块标题 - 18sp - Special Elite字体
    titleMedium = TextStyle(
        fontFamily = FontFamily.Monospace, // 替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 19.8.sp, // 1.1倍行间距
        letterSpacing = 0.sp
    )
)