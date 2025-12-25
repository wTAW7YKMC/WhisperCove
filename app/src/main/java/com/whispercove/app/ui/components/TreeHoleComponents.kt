package com.whispercove.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.Typography
import kotlin.math.sqrt

object TreeHoleComponents {
    
    // 邮票造型核心按钮（56dp正方形+锯齿边缘）
    @Composable
    fun StampButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true
    ) {
        // 使用主题颜色
        val buttonColor = if (enabled) MaterialTheme.colorScheme.primary else Color.Gray
        val borderColor = if (enabled) MaterialTheme.colorScheme.secondary else Color.Gray
        val textColor = if (enabled) MaterialTheme.colorScheme.onPrimary else Color.DarkGray
        
        // 点击时颜色变化动画
        val animatedColor by animateColorAsState(
            targetValue = buttonColor,
            animationSpec = tween(durationMillis = 100),
            label = "buttonColor"
        )
        
        Box(
            modifier = modifier
                .size(56.dp)
                .clip(TreeHoleStampShape()) // 邮票锯齿边缘
                .clickable(enabled = enabled) { 
                    onClick()
                }
                .background(color = animatedColor)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = TreeHoleStampShape()
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            // 中心添加微型树洞图标
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 树洞图标（简化版）
                val color = MaterialTheme.colorScheme.primary
                Canvas(
                    modifier = Modifier.size(16.dp)
                ) {
                    val size = 16.dp.toPx()
                    
                    // 绘制简化的树洞图标
                    drawCircle(
                        color = color,
                        radius = size / 2,
                        center = center
                    )
                    
                    // 绘制树洞入口
                    drawCircle(
                        color = Color.Black.copy(alpha = 0.3f),
                        radius = size / 3,
                        center = center
                    )
                }
                
                Spacer(modifier = Modifier.height(2.dp))
                
                Text(
                    text = text,
                    color = textColor,
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace, // Special Elite
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    
    // 木屋质感输入框
    @Composable
    fun WoodHouseInputField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        placeholder: String = "",
        isResponseInput: Boolean = false, // 区分信件输入框和回应输入框
        maxLength: Int = 200,
        minHeight: Float = 80f,
        isPacking: Boolean = false // 是否正在打包信件
    ) {
        val borderColor = if (isResponseInput) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
        val hasValue = value.isNotEmpty()
        
        Column(modifier = modifier) {
            // 输入框主体
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minHeight.dp, max = 200.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )
                    .drawBehind {
                        // 添加浅木纹暗纹（横向纹理）
                        val lineColor = Color(0xFF8D6E63).copy(alpha = 0.05f) // 使用固定颜色代替MaterialTheme
                        val strokeWidth = 1.dp.toPx()
                        
                        // 绘制横向木纹线条
                        for (i in 10..size.height.toInt() step 8) {
                            drawLine(
                                start = Offset(0f, i.toFloat()),
                                end = Offset(size.width, i.toFloat()),
                                color = lineColor,
                                strokeWidth = strokeWidth
                            )
                        }
                    }
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = borderColor
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp, 12.dp)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = { if (it.length <= maxLength) onValueChange(it) },
                    textStyle = TextStyle(
                        fontFamily = if (isResponseInput) FontFamily.Cursive else FontFamily.Monospace, // Patrick Hand 或 Special Elite
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.fillMaxSize(),
                    decorationBox = { innerTextField ->
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = TextStyle(
                                    fontFamily = FontFamily.Monospace, // Special Elite
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                )
                            )
                        }
                        innerTextField()
                    }
                )
                
                // 输入框右下角添加微型邮票图标（带打包信件动效）
                TreeHoleAnimations.PackLetterAnimation(
                    isVisible = isPacking,
                    onAnimationEnd = { /* 动画结束回调 */ }
                ) { iconColor ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp)
                    ) {
                        Canvas(
                            modifier = Modifier.size(12.dp)
                        ) {
                            with(TreeHoleIcons) {
                                drawStampIcon(12.dp.toPx(), iconColor)
                            }
                        }
                    }
                }
            }
            
            // 字数提示
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${value.length}/$maxLength",
                    style = TextStyle(
                        fontFamily = FontFamily.Default, // 思源黑体
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    }
    
    // 未拆信件卡片（信封+木屋风格）
    @Composable
    fun UnopenedLetterCard(
        mood: String,
        createTime: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        isUnboxing: Boolean = false // 是否正在拆信件
    ) {
        // 使用拆信件动效
        TreeHoleAnimations.UnboxLetterAnimation(
            isVisible = isUnboxing,
            onAnimationEnd = { /* 动画结束回调 */ }
        ) { animationState ->
            // 根据动画状态确定边框样式
            val borderWidth = if (animationState >= 2) 1.dp else 2.dp
            val borderColor = if (animationState >= 1) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
            
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = MaterialTheme.colorScheme.background)
                    .drawBehind {
                        // 添加树洞轮廓暗纹
                        drawTreeHoleTexture(size, 0.05f)
                    }
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onClick() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // 右上角添加火漆印图标（动画第二帧时消失）
                if (animationState < 2) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-8).dp, y = 8.dp)
                    ) {
                        val secondaryColor = MaterialTheme.colorScheme.secondary
                        val primaryColor = MaterialTheme.colorScheme.primary
                        Canvas(
                            modifier = Modifier.size(24.dp)
                        ) {
                            // 直接绘制火漆印图标，不使用with(TreeHoleIcons)
                            val size = 24.dp.toPx()
                            val radius = size / 2
                            
                            // 外圈
                            drawCircle(
                                color = secondaryColor,
                                radius = radius,
                                center = center
                            )
                            
                            // 内圈
                            drawCircle(
                                color = primaryColor,
                                radius = radius * 0.7f,
                                center = center
                            )
                        }
                    }
                }
                
                // 内容排版：标签+创建时间
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // 标签
                    Text(
                        text = mood,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace, // Special Elite
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // 创建时间
                    Text(
                        text = createTime,
                        style = TextStyle(
                            fontFamily = FontFamily.Default, // 思源黑体
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    )
                }
            }
        }
    }
    
    // 已拆信件卡片（信件+树洞风格）
    @Composable
    fun OpenedLetterCard(
        content: String,
        mood: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        maxLines: Int = 2
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .drawBehind {
                    // 添加浅木纹暗纹
                    val lineColor = Color(0xFF8D6E63).copy(alpha = 0.03f) // 使用固定颜色代替MaterialTheme
                    val strokeWidth = 1.dp.toPx()
                    
                    // 绘制横向木纹线条
                    for (i in 10..size.height.toInt() step 10) {
                        drawLine(
                            start = Offset(0f, i.toFloat()),
                            end = Offset(size.width, i.toFloat()),
                            color = lineColor,
                            strokeWidth = strokeWidth
                        )
                    }
                }
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            // 左上角添加邮票造型小标签
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-8).dp, y = (-8).dp)
                    .size(32.dp)
                    .clip(TreeHoleStampShape()) // 邮票锯齿边框
                    .background(color = MaterialTheme.colorScheme.primary)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = TreeHoleStampShape()
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (mood.isNotEmpty()) mood[0].toString() else "信",
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace, // Special Elite
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            
            // 信件文本
            Text(
                text = content,
                style = TextStyle(
                    fontFamily = FontFamily.Monospace, // Special Elite
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 19.2.sp // 1.2倍行间距
                ),
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    
    // 回信卡片（手写回信+树洞风格）
    @Composable
    fun ResponseCard(
        content: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        maxLines: Int = 3
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .drawBehind {
                    // 添加手写横线暗纹
                    val lineColor = Color(0xFF8D6E63).copy(alpha = 0.05f) // 使用固定颜色代替MaterialTheme
                    val strokeWidth = 1.dp.toPx()
                    
                    // 绘制手写横线
                    for (i in 20..size.height.toInt() step 20) {
                        drawLine(
                            start = Offset(0f, i.toFloat()),
                            end = Offset(size.width, i.toFloat()),
                            color = lineColor,
                            strokeWidth = strokeWidth
                        )
                    }
                }
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onClick() }
                .padding(12.dp)
        ) {
            // 左侧添加树洞轮廓竖线
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            
            // 回信文本
            Text(
                text = content,
                style = TextStyle(
                    fontFamily = FontFamily.Cursive, // Patrick Hand
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 20.8.sp // 1.3倍行间距
                ),
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }
    }
    
    // 标签组件（木屋/邮票融合）
    @Composable
    fun TagButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        selected: Boolean = false
    ) {
        // 使用标签选中动效
        TreeHoleAnimations.TagSelectAnimation(isSelected = selected) { backgroundColor, textColor ->
            Button(
                onClick = onClick,
                modifier = modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .height(32.dp)
                    .clip(TreeHoleTagShape()), // 轻微锯齿边缘,
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 标签左侧添加微型树叶图标
                    val color = MaterialTheme.colorScheme.primary
                    Canvas(
                        modifier = Modifier.size(8.dp)
                    ) {
                        // 直接绘制树叶图标，不使用with(TreeHoleIcons)
                        val size = 8.dp.toPx()
                        
                        // 绘制简化的树叶形状
                        drawPath(
                            path = Path().apply {
                                moveTo(size * 0.5f, 0f)
                                lineTo(size * 0.8f, size * 0.3f)
                                lineTo(size * 0.7f, size * 0.6f)
                                lineTo(size * 0.5f, size * 0.5f)
                                lineTo(size * 0.3f, size * 0.6f)
                                lineTo(size * 0.2f, size * 0.3f)
                                close()
                            },
                            color = color
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    Text(
                        text = text,
                        color = textColor,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace, // Special Elite
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

// 邮票锯齿边缘形状
class TreeHoleStampShape : androidx.compose.ui.graphics.Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ) = androidx.compose.ui.graphics.Outline.Generic(Path().apply {
        val sawtoothDepth = with(density) { 3.dp.toPx() }
        val sawtoothSpacing = with(density) { 6.dp.toPx() }
        
        // 绘制锯齿边缘
        // 上边
        var x = 0f
        while (x < size.width) {
            if (x + sawtoothSpacing > size.width) {
                lineTo(size.width, 0f)
                break
            }
            lineTo(x + sawtoothSpacing / 2, sawtoothDepth)
            lineTo(x + sawtoothSpacing, 0f)
            x += sawtoothSpacing
        }
        
        // 右边
        var y = 0f
        while (y < size.height) {
            if (y + sawtoothSpacing > size.height) {
                lineTo(size.width, size.height)
                break
            }
            lineTo(size.width - sawtoothDepth, y + sawtoothSpacing / 2)
            lineTo(size.width, y + sawtoothSpacing)
            y += sawtoothSpacing
        }
        
        // 下边
        x = size.width
        while (x > 0) {
            if (x - sawtoothSpacing < 0) {
                lineTo(0f, size.height)
                break
            }
            lineTo(x - sawtoothSpacing / 2, size.height - sawtoothDepth)
            lineTo(x - sawtoothSpacing, size.height)
            x -= sawtoothSpacing
        }
        
        // 左边
        y = size.height
        while (y > 0) {
            if (y - sawtoothSpacing < 0) {
                lineTo(0f, 0f)
                break
            }
            lineTo(sawtoothDepth, y - sawtoothSpacing / 2)
            lineTo(0f, y - sawtoothSpacing)
            y -= sawtoothSpacing
        }
        
        close()
    })
}

// 标签锯齿边缘形状（轻微锯齿）
class TreeHoleTagShape : androidx.compose.ui.graphics.Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ) = androidx.compose.ui.graphics.Outline.Generic(Path().apply {
        val sawtoothDepth = with(density) { 1.dp.toPx() }
        val sawtoothSpacing = with(density) { 8.dp.toPx() }
        
        // 绘制轻微锯齿边缘
        // 上边
        var x = 0f
        while (x < size.width) {
            if (x + sawtoothSpacing > size.width) {
                lineTo(size.width, 0f)
                break
            }
            lineTo(x + sawtoothSpacing / 2, sawtoothDepth)
            lineTo(x + sawtoothSpacing, 0f)
            x += sawtoothSpacing
        }
        
        // 右边
        var y = 0f
        while (y < size.height) {
            if (y + sawtoothSpacing > size.height) {
                lineTo(size.width, size.height)
                break
            }
            lineTo(size.width - sawtoothDepth, y + sawtoothSpacing / 2)
            lineTo(size.width, y + sawtoothSpacing)
            y += sawtoothSpacing
        }
        
        // 下边
        x = size.width
        while (x > 0) {
            if (x - sawtoothSpacing < 0) {
                lineTo(0f, size.height)
                break
            }
            lineTo(x - sawtoothSpacing / 2, size.height - sawtoothDepth)
            lineTo(x - sawtoothSpacing, size.height)
            x -= sawtoothSpacing
        }
        
        // 左边
        y = size.height
        while (y > 0) {
            if (y - sawtoothSpacing < 0) {
                lineTo(0f, 0f)
                break
            }
            lineTo(sawtoothDepth, y - sawtoothSpacing / 2)
            lineTo(0f, y - sawtoothSpacing)
            y -= sawtoothSpacing
        }
        
        close()
    })
}