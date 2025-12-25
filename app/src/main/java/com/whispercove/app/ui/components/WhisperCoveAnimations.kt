package com.whispercove.app.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.*

/**
 * WhisperCove 动画组件
 * 实现三种核心动效：
 * 1. 拆盲盒动效：帧动画（2帧），300ms
 * 2. 打包盲盒动效：帧动画（1帧），200ms
 * 3. 标签选中动效：颜色切换，100ms
 */
object WhisperCoveAnimations {
    
    /**
     * 拆盲盒动效
     * 帧动画（2帧）：
     * 1. 未拆卡片边框变stamp_red
     * 2. 火漆印消失，卡片边框变细
     * 时长：300ms
     */
    @Composable
    fun UnboxBlindBoxAnimation(
        isVisible: Boolean,
        onAnimationEnd: () -> Unit = {},
        content: @Composable (animationState: Int) -> Unit
    ) {
        var animationState by remember { mutableStateOf(0) }
        
        LaunchedEffect(isVisible) {
            if (isVisible) {
                // 第一帧：边框变红
                animationState = 1
                delay(150) // 150ms后进入第二帧
                
                // 第二帧：火漆印消失，边框变细
                animationState = 2
                delay(150) // 再150ms后结束
                
                onAnimationEnd()
            }
        }
        
        content(animationState)
    }
    
    /**
     * 打包盲盒动效
     * 帧动画（1帧）：
     * 输入框右下角邮票图标变亮（stamp_red→#D94A42）
     * 时长：200ms
     */
    @Composable
    fun PackBlindBoxAnimation(
        isVisible: Boolean,
        onAnimationEnd: () -> Unit = {},
        content: @Composable (iconColor: Color) -> Unit
    ) {
        val iconColor = if (isVisible) {
            Color(0xFFD94A42) // stamp_red 变亮
        } else {
            Color(0xFFC83E37) // 原始 stamp_red
        }
        
        LaunchedEffect(isVisible) {
            if (isVisible) {
                delay(200) // 200ms后结束
                onAnimationEnd()
            }
        }
        
        content(iconColor)
    }
    
    /**
     * 标签选中动效
     * 颜色切换（无渐变，直接切换）：
     * wood_brown_light→stamp_red
     * 时长：100ms
     */
    @Composable
    fun TagSelectAnimation(
        isSelected: Boolean,
        content: @Composable (backgroundColor: Color, textColor: Color) -> Unit
    ) {
        val backgroundColor = if (isSelected) {
            Color(0xFFC83E37) // stamp_red
        } else {
            Color(0xFFA68A69) // wood_brown_light
        }
        
        val textColor = if (isSelected) {
            Color(0xFFF9F6F3) // bg_paper
        } else {
            Color(0xFF222222) // text_black
        }
        
        content(backgroundColor, textColor)
    }
}

/**
 * 绘制图标函数
 * 用于在各个组件中绘制装饰性图标
 */
object WhisperCoveIcons {
    
    /**
     * 绘制树洞图标
     */
    fun DrawScope.drawTreeHoleIcon(size: Float, color: Color) {
        val strokeWidth = 2.dp.toPx()
        val center = Offset(size / 2, size / 2)
        val radius = size / 3
        
        // 绘制不规则的树洞轮廓
        for (angle in 0..360 step 30) {
            val radian = Math.toRadians(angle.toDouble())
            val x1 = center.x + radius * cos(radian).toFloat()
            val y1 = center.y + radius * sin(radian).toFloat()
            val x2 = center.x + (radius * 0.7f) * cos(radian).toFloat()
            val y2 = center.y + (radius * 0.7f) * sin(radian).toFloat()
            
            drawLine(
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }
    
    /**
     * 绘制邮票图标
     */
    fun DrawScope.drawStampIcon(size: Float, color: Color) {
        val strokeWidth = 1.dp.toPx()
        val padding = size * 0.2f
        
        // 绘制邮票锯齿边框
        val path = Path().apply {
            val sawtoothDepth = size * 0.1f
            val sawtoothSpacing = size * 0.15f
            
            // 绘制锯齿边缘
            // 上边
            var x = padding
            while (x < size - padding) {
                if (x + sawtoothSpacing > size - padding) {
                    lineTo(size - padding, padding)
                    break
                }
                lineTo(x + sawtoothSpacing / 2, padding + sawtoothDepth)
                lineTo(x + sawtoothSpacing, padding)
                x += sawtoothSpacing
            }
            
            // 右边
            var y = padding
            while (y < size - padding) {
                if (y + sawtoothSpacing > size - padding) {
                    lineTo(size - padding, size - padding)
                    break
                }
                lineTo(size - padding - sawtoothDepth, y + sawtoothSpacing / 2)
                lineTo(size - padding, y + sawtoothSpacing)
                y += sawtoothSpacing
            }
            
            // 下边
            x = size - padding
            while (x > padding) {
                if (x - sawtoothSpacing < padding) {
                    lineTo(padding, size - padding)
                    break
                }
                lineTo(x - sawtoothSpacing / 2, size - padding - sawtoothDepth)
                lineTo(x - sawtoothSpacing, size - padding)
                x -= sawtoothSpacing
            }
            
            // 左边
            y = size - padding
            while (y > padding) {
                if (y - sawtoothSpacing < padding) {
                    lineTo(padding, padding)
                    break
                }
                lineTo(padding + sawtoothDepth, y - sawtoothSpacing / 2)
                lineTo(padding, y - sawtoothSpacing)
                y -= sawtoothSpacing
            }
            
            close()
        }
        
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
    
    /**
     * 绘制火漆印图标
     */
    fun DrawScope.drawSealIcon(size: Float, fillColor: Color, borderColor: Color) {
        val center = Offset(size / 2, size / 2)
        val radius = size / 2.5f
        
        // 绘制圆形火漆印
        drawCircle(
            color = fillColor,
            radius = radius,
            center = center
        )
        
        // 绘制边框
        drawCircle(
            color = borderColor,
            radius = radius,
            center = center,
            style = Stroke(width = 1.dp.toPx())
        )
        
        // 绘制简单的W字母（WhisperCove首字母）
        val wPath = Path().apply {
            val wWidth = radius * 1.2f
            val wHeight = radius * 1.2f
            val startX = center.x - wWidth / 2
            val startY = center.y - wHeight / 2
            
            // 绘制W
            moveTo(startX, startY + wHeight)
            lineTo(startX + wWidth * 0.25f, startY)
            lineTo(startX + wWidth * 0.5f, startY + wHeight * 0.6f)
            lineTo(startX + wWidth * 0.75f, startY)
            lineTo(startX + wWidth, startY + wHeight)
        }
        
        drawPath(
            path = wPath,
            color = borderColor,
            style = Stroke(width = 1.dp.toPx())
        )
    }
    
    /**
     * 绘制树叶图标
     */
    fun DrawScope.drawLeafIcon(size: Float, color: Color) {
        val center = Offset(size / 2, size / 2)
        val leafWidth = size * 0.8f
        val leafHeight = size * 1.2f
        
        // 绘制简单的叶子形状
        val leafPath = Path().apply {
            val startX = center.x
            val startY = center.y - leafHeight / 2
            
            moveTo(startX, startY)
            
            // 右侧叶子轮廓
            quadraticBezierTo(
                x1 = startX + leafWidth / 2,
                y1 = startY + leafHeight * 0.3f,
                x2 = startX + leafWidth / 3,
                y2 = startY + leafHeight * 0.7f
            )
            
            // 叶子尖端
            lineTo(startX, startY + leafHeight)
            
            // 左侧叶子轮廓
            lineTo(startX - leafWidth / 3, startY + leafHeight * 0.7f)
            
            quadraticBezierTo(
                x1 = startX - leafWidth / 2,
                y1 = startY + leafHeight * 0.3f,
                x2 = startX, startY
            )
            
            close()
        }
        
        drawPath(
            path = leafPath,
            color = color
        )
        
        // 绘制叶脉
        drawLine(
            start = center,
            end = Offset(center.x, center.y + leafHeight / 2),
            color = color,
            strokeWidth = 0.5.dp.toPx()
        )
    }
}