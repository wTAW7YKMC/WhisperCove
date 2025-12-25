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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.*

/**
 * 瓶子画布组件
 */
@Composable
private fun BottleCanvas(
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Canvas(
        modifier = modifier
    ) {
        drawBottle(primaryColor)
    }
}

/**
 * 漂流瓶组件 - 用于回信功能
 * @param bottleType 漂流瓶类型
 * @param content 瓶中信件内容
 * @param waxSealType 火漆印类型
 * @param onClick 点击回调
 * @param modifier 修饰符
 */
@Composable
fun DriftingBottle(
    bottleType: BottleType,
    content: String,
    waxSealType: WaxSealType,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 漂流瓶主体
        Box(
            modifier = Modifier
                .size(100.dp)
                .rotate(bottleType.rotation)
        ) {
            BottleCanvas()
            
            // 火漆印标签
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(15.dp))
                    .background(waxSealType.color)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = waxSealType.icon,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 瓶中信件预览
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                    text = if (content.length > 20) content.take(20) + "..." else content,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 10.sp,
                    maxLines = 2
                )
        }
    }
}

/**
 * 绘制漂流瓶形状
 */
private fun DrawScope.drawBottle(primaryColor: Color) {
    val bottleWidth = size.width * 0.6f
    val bottleHeight = size.height * 0.7f
    val bottleNeckWidth = size.width * 0.3f
    val bottleNeckHeight = size.height * 0.15f
    val bottleCapWidth = size.width * 0.4f
    val bottleCapHeight = size.height * 0.1f
    
    // 瓶身
    drawRoundRect(
        color = Color.Transparent,
        topLeft = Offset(
            (size.width - bottleWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2 + bottleNeckHeight + bottleCapHeight
        ),
        size = Size(bottleWidth, bottleHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(bottleWidth / 2, bottleWidth / 2)
    )
    
    // 瓶颈
    drawRoundRect(
        color = Color.Transparent,
        topLeft = Offset(
            (size.width - bottleNeckWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2 + bottleCapHeight
        ),
        size = Size(bottleNeckWidth, bottleNeckHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
    )
    
    // 瓶塞
    drawRoundRect(
        color = primaryColor,
        topLeft = Offset(
            (size.width - bottleCapWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2
        ),
        size = Size(bottleCapWidth, bottleCapHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
    )
}

/**
 * 羁绊漂流瓶组件 - 用于回信功能，带有羁绊标识
 */
@Composable
fun BondedBottle(
    content: String,
    userWaxSeal: WaxSealType,
    partnerWaxSeal: WaxSealType,
    remainingMessages: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 羁绊漂流瓶主体
        Box(
            modifier = Modifier.size(100.dp)
        ) {
            // 瓶身
            BottleCanvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFE1F5FE)) // 浅蓝色，表示羁绊
            )
            
            // 双方火漆印标签
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(userWaxSeal.color)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userWaxSeal.icon,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
                
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(partnerWaxSeal.color)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = partnerWaxSeal.icon,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
            
            // 羁绊标识
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = remainingMessages.toString(),
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 瓶中信件预览
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (content.length > 20) content.take(20) + "..." else content,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 10.sp,
                maxLines = 2
            )
        }
        
        // 羁绊状态文字
        Text(
            text = "羁绊信件 ($remainingMessages/3)",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 10.sp
        )
    }
}

/**
 * 瓶子形状画布组件
 */
@Composable
private fun BottleShapeCanvas(
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Canvas(
        modifier = modifier
    ) {
        drawBottleShape(primaryColor)
    }
}

/**
 * 漂流瓶输入组件 - 用于输入回信内容
 */
@Composable
fun BottleMessageInput(
    content: String,
    onContentChange: (String) -> Unit,
    waxSealType: WaxSealType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF0F8FF)) // 浅蓝色背景
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // 瓶子形状背景
        BottleShapeCanvas(
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
                cursorColor = waxSealType.color
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            ),
            placeholder = {
                Text(
                    text = "在漂流瓶标签上写下回信...",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    fontSize = 16.sp
                )
            }
        )
        
        // 火漆印
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(30.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(waxSealType.color)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = waxSealType.icon,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

/**
 * 绘制瓶子形状背景
 */
private fun DrawScope.drawBottleShape(primaryColor: Color) {
    val bottleWidth = size.width * 0.8f
    val bottleHeight = size.height * 0.9f
    val bottleNeckWidth = size.width * 0.4f
    val bottleNeckHeight = size.height * 0.15f
    val bottleCapWidth = size.width * 0.5f
    val bottleCapHeight = size.height * 0.1f
    
    // 瓶身
    drawRoundRect(
        color = Color.White.copy(alpha = 0.3f),
        topLeft = Offset(
            (size.width - bottleWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2 + bottleNeckHeight + bottleCapHeight
        ),
        size = Size(bottleWidth, bottleHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(bottleWidth / 2, bottleWidth / 2)
    )
    
    // 瓶颈
    drawRoundRect(
        color = Color.White.copy(alpha = 0.3f),
        topLeft = Offset(
            (size.width - bottleNeckWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2 + bottleCapHeight
        ),
        size = Size(bottleNeckWidth, bottleNeckHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
    )
    
    // 瓶塞
    drawRoundRect(
        color = primaryColor.copy(alpha = 0.5f),
        topLeft = Offset(
            (size.width - bottleCapWidth) / 2,
            (size.height - bottleHeight - bottleNeckHeight - bottleCapHeight) / 2
        ),
        size = Size(bottleCapWidth, bottleCapHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
    )
}

/**
 * 漂流瓶类型枚举
 */
enum class BottleType(
    val displayName: String,
    val color: Color,
    val rotation: Float
) {
    GREEN(
        "绿色漂流瓶",
        Color(0xFFA5D6A7),
        0f
    ),
    BLUE(
        "蓝色漂流瓶",
        Color(0xFF90CAF9),
        15f
    ),
    BROWN(
        "棕色漂流瓶",
        Color(0xFFBCAAA4),
        -10f
    )
}