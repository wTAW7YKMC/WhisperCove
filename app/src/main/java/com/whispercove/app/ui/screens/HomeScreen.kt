package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whispercove.app.ui.models.MockData
import com.whispercove.app.ui.components.WhisperCard
import com.whispercove.app.ui.components.PrintComponents
import com.whispercove.app.ui.theme.SealRed

@Composable
fun HomeScreen(navController: NavController) {
    val posts = remember { MockData.posts }
    val moodTags = listOf("全部", "喜悦", "平静", "兴奋", "思考", "期待")
    var selectedMood by remember { mutableStateOf("全部") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            // 添加极淡的"信件堆叠"背景暗纹
            .drawBehind {
                // 绘制极淡的信件堆叠暗纹
                val lineColor = Color(0xFF222222).copy(alpha = 0.03f) // 使用固定颜色而非MaterialTheme
                
                // 绘制水平线条模拟信件堆叠
                for (i in 5..size.height.toInt() step 40) {
                    drawLine(
                        start = Offset(0f, i.toFloat()),
                        end = Offset(size.width, i.toFloat()),
                        color = lineColor,
                        strokeWidth = 1.dp.toPx()
                    )
                }
                
                // 绘制垂直线条模拟信件边缘
                for (i in 20..size.width.toInt() step 60) {
                    drawLine(
                        start = Offset(i.toFloat(), 0f),
                        end = Offset(i.toFloat(), size.height),
                        color = lineColor,
                        strokeWidth = 0.5.dp.toPx()
                    )
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            // 顶部：居中标题"WhisperCove"（Special Elite字体，墨黑+印泥红下划线）+右侧小邮票图标
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 空白占位，使标题居中
                Box(modifier = Modifier.size(24.dp))
                
                // 居中标题
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = FontFamily.Monospace // 替代Special Elite字体
                            )
                        ) {
                            append("WhisperCove")
                        }
                    },
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.drawBehind {
                        // 绘制印泥红下划线
                        val strokeWidth = 2.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        
                        drawLine(
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            color = SealRed,
                            strokeWidth = strokeWidth
                        )
                    }
                )
                
                // 右侧小邮票图标
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = "Mail",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 中部：筛选标签栏（横向滚动，印泥红矩形标签）
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(moodTags) { mood ->
                    PrintComponents.TagButton(
                        text = mood,
                        onClick = { selectedMood = mood },
                        selected = selectedMood == mood
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 盲盒池（2列网格，未拆信封卡片，右上角火漆印）
            val filteredPosts = if (selectedMood == "全部") {
                posts
            } else {
                posts.filter { it.mood == selectedMood }
            }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredPosts.take(6)) { post -> // 限制显示6个盲盒
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = MaterialTheme.shapes.small
                            )
                    ) {
                        // 信封卡片内容
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = post.userName,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = post.content.take(50) + if (post.content.length > 50) "..." else "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 3
                            )
                        }
                        
                        // 右上角火漆印
                        PrintComponents.SealDecoration(
                            modifier = Modifier.align(Alignment.TopEnd),
                            size = 24f,
                            color = SealRed
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 印刷分割线
            val onSurfaceColor = MaterialTheme.colorScheme.onSurface
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(onSurfaceColor.copy(alpha = 0.2f))
                    .drawBehind {
                        // 添加印刷风格的虚线效果
                        val dashWidth = 8.dp.toPx()
                        val dashGap = 4.dp.toPx()
                        var x = 0f
                        
                        while (x < size.width) {
                            drawLine(
                                start = Offset(x, 0f),
                                end = Offset((x + dashWidth).coerceAtMost(size.width), 0f),
                                color = onSurfaceColor.copy(alpha = 0.4f),
                                strokeWidth = 1.dp.toPx()
                            )
                            x += dashWidth + dashGap
                        }
                    }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 底部：居中核心按钮（邮票造型"抽取盲盒"）
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                PrintComponents.StampButton(
                    text = "抽取盲盒",
                    onClick = { /* TODO: 实现抽取盲盒功能 */ }
                )
            }
        }
    }
}