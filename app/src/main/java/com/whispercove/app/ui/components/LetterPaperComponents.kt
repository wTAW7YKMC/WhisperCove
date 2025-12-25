package com.whispercove.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.*

/**
 * 信纸组件 - 用于写信功能
 * @param letterPaperType 信纸类型
 * @param content 信件内容
 * @param onContentChange 内容变更回调
 * @param modifier 修饰符
 */
@Composable
fun LetterPaper(
    letterPaperType: LetterPaperType,
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(letterPaperType.backgroundColor)
            .border(
                width = 2.dp,
                color = letterPaperType.borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // 信纸纹理
        LetterPaperTexture(
            letterPaperType = letterPaperType,
            modifier = Modifier.fillMaxSize()
        )
        
        // 信件内容输入区
        TextField(
            value = content,
            onValueChange = onContentChange,
            modifier = Modifier.fillMaxSize(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = letterPaperType.textColor
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = letterPaperType.textColor,
                fontSize = 16.sp
            ),
            placeholder = {
                Text(
                    text = "在这里写下你的心情...",
                    color = letterPaperType.textColor.copy(alpha = 0.5f),
                    fontSize = 16.sp
                )
            }
        )
    }
}

/**
 * 信纸纹理组件
 */
@Composable
private fun LetterPaperTexture(
    letterPaperType: LetterPaperType,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    
    Canvas(modifier = modifier) {
        when (letterPaperType) {
            LetterPaperType.WOOD -> {
                // 木纹纹理
                drawWoodTexture(primaryColor)
            }
            LetterPaperType.LEAF -> {
                // 树叶纹理
                drawLeafTexture(secondaryColor)
            }
            LetterPaperType.STAMP -> {
                // 邮票边框
                drawStampBorder(tertiaryColor)
            }
        }
    }
}

/**
 * 绘制木纹纹理
 */
private fun DrawScope.drawWoodTexture(primaryColor: Color) {
    val stripeWidth = size.width * 0.05f
    val stripeSpacing = size.width * 0.15f
    
    for (i in 0 until (size.width / (stripeWidth + stripeSpacing)).toInt()) {
        val x = i * (stripeWidth + stripeSpacing)
        drawRect(
            color = primaryColor.copy(alpha = 0.1f),
            topLeft = Offset(x, 0f),
            size = Size(stripeWidth, size.height)
        )
    }
}

/**
 * 绘制树叶纹理
 */
private fun DrawScope.drawLeafTexture(secondaryColor: Color) {
    val leafSize = size.width * 0.08f
    val leafSpacing = size.width * 0.2f
    
    for (i in 0 until (size.width / leafSpacing).toInt()) {
        for (j in 0 until (size.height / leafSpacing).toInt()) {
            val x = i * leafSpacing + leafSpacing / 2
            val y = j * leafSpacing + leafSpacing / 2
            
            // 绘制简单的叶脉纹理
            drawLine(
                color = secondaryColor.copy(alpha = 0.1f),
                start = Offset(x, y - leafSize / 2),
                end = Offset(x, y + leafSize / 2),
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}

/**
 * 绘制邮票边框
 */
private fun DrawScope.drawStampBorder(tertiaryColor: Color) {
    val borderSize = 20.dp.toPx()
    val spacing = 10.dp.toPx()
    
    // 绘制邮票边缘的锯齿效果
    for (i in 0 until (size.width / (borderSize + spacing)).toInt()) {
        val x = i * (borderSize + spacing)
        drawCircle(
            color = tertiaryColor.copy(alpha = 0.2f),
            radius = 4.dp.toPx(),
            center = Offset(x, borderSize / 2)
        )
        drawCircle(
            color = tertiaryColor.copy(alpha = 0.2f),
            radius = 4.dp.toPx(),
            center = Offset(x, size.height - borderSize / 2)
        )
    }
    
    for (i in 0 until (size.height / (borderSize + spacing)).toInt()) {
        val y = i * (borderSize + spacing)
        drawCircle(
            color = tertiaryColor.copy(alpha = 0.2f),
            radius = 4.dp.toPx(),
            center = Offset(borderSize / 2, y)
        )
        drawCircle(
            color = tertiaryColor.copy(alpha = 0.2f),
            radius = 4.dp.toPx(),
            center = Offset(size.width - borderSize / 2, y)
        )
    }
}

/**
 * 信纸类型选择器组件
 */
@Composable
fun LetterPaperSelector(
    selectedType: LetterPaperType,
    onTypeSelected: (LetterPaperType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LetterPaperType.values().forEach { type ->
            Column(
                modifier = Modifier
                    .clickable { onTypeSelected(type) }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 信纸预览
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(type.backgroundColor)
                        .border(
                            width = if (selectedType == type) 3.dp else 1.dp,
                            color = if (selectedType == type) MaterialTheme.colorScheme.tertiary else type.borderColor,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // 信纸名称
                Text(
                    text = type.displayName,
                    color = if (selectedType == type) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    fontWeight = if (selectedType == type) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

/**
 * 火漆印组件
 */
@Composable
fun WaxSeal(
    sealType: WaxSealType,
    modifier: Modifier = Modifier,
    isPressed: Boolean = false
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(sealType.color)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        // 火漆印图标
        Text(
            text = sealType.icon,
            fontSize = 20.sp,
            color = Color.White
        )
        
        // 按压效果
        if (isPressed) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.1f))
            )
        }
    }
}

/**
 * 火漆印选择器组件
 */
@Composable
fun WaxSealSelector(
    selectedType: WaxSealType,
    onTypeSelected: (WaxSealType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WaxSealType.values().forEach { type ->
            Column(
                modifier = Modifier
                    .clickable { onTypeSelected(type) }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WaxSeal(
                    sealType = type,
                    isPressed = selectedType == type
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // 火漆印名称
                Text(
                    text = type.displayName,
                    color = if (selectedType == type) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    fontWeight = if (selectedType == type) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

/**
 * 信纸类型枚举
 */
enum class LetterPaperType(
    val displayName: String,
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color
) {
    WOOD(
        "木纹信纸",
        Color(0xFFF5F0E8),
        Color(0xFF8D6E63), // 棕色
        Color(0xFF212121)  // 深灰色
    ),
    LEAF(
        "树叶信纸",
        Color(0xFFF5FAF5),
        Color(0xFF66BB6A), // 绿色
        Color(0xFF212121)  // 深灰色
    ),
    STAMP(
        "邮票信纸",
        Color(0xFFFFF8F0),
        Color(0xFFEF5350), // 红色
        Color(0xFF212121)  // 深灰色
    )
}

/**
 * 火漆印类型枚举
 */
enum class WaxSealType(
    val displayName: String,
    val icon: String,
    val color: Color
) {
    SUN(
        "太阳印章",
        "☀",
        Color(0xFFFFB74D) // 橙黄色
    ),
    RAIN(
        "雨滴印章",
        "💧",
        Color(0xFF64B5F6) // 浅蓝色
    ),
    LEAF(
        "树叶印章",
        "🍃",
        Color(0xFF81C784) // 浅绿色
    )
}