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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.*

/**
 * 木屋组件 - 作为APP首页的主要视觉元素
 * @param modifier 修饰符
 * @param onMailboxClick 信箱点击回调
 * @param onCollectionBoxClick 收藏盒点击回调
 * @param pendingLettersCount 待取信件数量
 */
@Composable
fun WoodenHouse(
    modifier: Modifier = Modifier,
    onMailboxClick: () -> Unit = {},
    onCollectionBoxClick: () -> Unit = {},
    pendingLettersCount: Int = 0
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        // 木屋主体
        WoodenHouseBody()
        
        // 木屋屋顶
        WoodenHouseRoof()
        
        // 信箱
        WoodenMailbox(
            modifier = Modifier.align(Alignment.BottomStart).offset(x = 40.dp, y = (-20).dp),
            pendingLettersCount = pendingLettersCount,
            onClick = onMailboxClick
        )
        
        // 收藏盒
        CollectionBox(
            modifier = Modifier.align(Alignment.BottomEnd).offset(x = (-40).dp, y = (-10).dp),
            onClick = onCollectionBoxClick
        )
        
        // 情绪天气牌
        WeatherBoard(
            modifier = Modifier.align(Alignment.TopCenter).offset(y = 20.dp)
        )
    }
}

/**
 * 木屋主体组件
 */
@Composable
private fun WoodenHouseBody() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
    ) {
        // 木屋窗户
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(60.dp)
                .height(80.dp)
                .background(
                    color = Color(0xFFF9F6F3),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
        )
        
        // 木屋门
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(50.dp)
                .height(70.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                )
                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
        )
    }
}

/**
 * 木屋屋顶组件
 */
@Composable
private fun WoodenHouseRoof() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    
    Canvas(
        modifier = Modifier
            .width(240.dp)
            .height(80.dp)
            .offset(y = (-40).dp)
    ) {
        // 绘制三角形屋顶
        val path = androidx.compose.ui.graphics.Path().apply {
            moveTo(size.width / 2, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            close()
        }
        drawPath(path, color = secondaryColor)
        
        // 屋顶边框
        drawPath(
            path = path,
            color = primaryColor,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx())
        )
    }
}

/**
 * 木屋信箱组件
 */
@Composable
fun WoodenMailbox(
    modifier: Modifier = Modifier,
    pendingLettersCount: Int = 0,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 信箱主体
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
        ) {
            // 信箱门
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
            
            // 待取信件数量
            if (pendingLettersCount > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(24.dp)
                        .height(24.dp)
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (pendingLettersCount > 99) "99+" else pendingLettersCount.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        // 信箱标识
        Text(
            text = "信箱",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp
        )
    }
}

/**
 * 收藏盒组件
 */
@Composable
fun CollectionBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 收藏盒主体
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            // 收藏盒盖
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                    )
            )
        }
        
        // 收藏盒标识
        Text(
            text = "藏信",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp
        )
    }
}

/**
 * 情绪天气牌组件
 */
@Composable
fun WeatherBoard(
    modifier: Modifier = Modifier,
    weatherType: WeatherType = WeatherType.CLOUDY
) {
    Box(
        modifier = modifier
            .width(120.dp)
            .height(40.dp)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                shape = RoundedCornerShape(20.dp)
            )
            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // 天气图标
            Text(
                text = when (weatherType) {
                    WeatherType.SUNNY -> "☀"
                    WeatherType.RAINY -> "☔"
                    WeatherType.CLOUDY -> "☁"
                },
                fontSize = 20.sp
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            // 天气文字
            Text(
                text = when (weatherType) {
                    WeatherType.SUNNY -> "晴天"
                    WeatherType.RAINY -> "小雨"
                    WeatherType.CLOUDY -> "多云"
                },
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
    }
}

/**
 * 天气类型枚举
 */
enum class WeatherType {
    SUNNY,    // 晴天 - 开心类信件居多
    RAINY,    // 小雨 - emo类信件居多
    CLOUDY    // 多云 - 其他情况
}