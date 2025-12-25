package com.whispercove.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// WhisperCove 字体规范
// 英文核心字体：Special Elite（复古印刷体，呼应简历打字机风格）
// 英文手写字体：Patrick Hand（仅用于"回应文本"，模拟手写回信）
// 中文统一字体：思源黑体-常规版（和简历完全一致）

// 注意：暂时使用系统默认字体，后续可添加自定义字体文件
val Typography = Typography(
    // 页面标题 - 22sp - Special Elite字体（复古印刷体）
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default, // 临时使用系统默认字体，替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 24.2.sp, // 1.1倍行间距
        letterSpacing = 0.sp
    ),
    // 模块标题 - 18sp - Special Elite字体
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default, // 临时使用系统默认字体，替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 19.8.sp, // 1.1倍行间距
        letterSpacing = 0.sp
    ),
    // 盲盒文本（印刷信件）- 16sp - Special Elite字体（印刷体信件）
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // 临时使用系统默认字体，替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.2.sp, // 1.2倍行间距
        letterSpacing = 0.sp
    ),
    // 回应文本（手写回信）- 16sp - Patrick Hand字体
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default, // 临时使用系统默认字体，替代Patrick Hand
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.8.sp, // 1.3倍行间距
        letterSpacing = 0.5.sp // 手写体稍宽字间距
    ),
    // 按钮/标签文字 - 14sp - Special Elite字体
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default, // 临时使用系统默认字体，替代Special Elite
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp, // 1.0倍行间距
        letterSpacing = 0.sp
    ),
    // 辅助文字（时间戳/说明）- 12sp - 思源黑体
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default, // 系统默认字体，替代思源黑体
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp, // 1.0倍行间距
        letterSpacing = 0.sp
    )
)