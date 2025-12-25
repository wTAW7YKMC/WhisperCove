package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.whispercove.app.ui.components.WhisperCoveComponents
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val posts = remember { MockData.posts }
    val moodTags = listOf("全部", "考研emo", "干饭快乐", "平静", "兴奋", "思考", "期待")
    var selectedMood by remember { mutableStateOf("全部") }
    var unboxingCardId by remember { mutableStateOf<String?>(null) } // 正在拆盲盒的卡片ID
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6F3)) // bg_paper
            // 添加极淡的树洞轮廓暗纹
            .drawBehind {
                // 绘制极淡的树洞轮廓暗纹
                val lineColor = Color(0xFF6B8E5D).copy(alpha = 0.05f) // tree_green with 5% opacity
                
                // 绘制树洞轮廓
                val centerX = size.width * 0.8f
                val centerY = size.height * 0.2f
                val radius = minOf(size.width, size.height) * 0.1f
                
                // 绘制不规则的树洞轮廓
                for (angle in 0..360 step 30) {
                    val radian = Math.toRadians(angle.toDouble())
                    val x1 = centerX + radius * kotlin.math.cos(radian).toFloat()
                    val y1 = centerY + radius * kotlin.math.sin(radian).toFloat()
                    val x2 = centerX + (radius * 0.7f) * kotlin.math.cos(radian).toFloat()
                    val y2 = centerY + (radius * 0.7f) * kotlin.math.sin(radian).toFloat()
                    
                    drawLine(
                        start = Offset(x1, y1),
                        end = Offset(x2, y2),
                        color = lineColor,
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp) // page_margin_horizontal 和 page_margin_vertical
        ) {
            // 顶部：居中标题"WhisperCove"（Special Elite字体，墨黑+木屋棕下划线）+右侧木屋图标
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
                                color = Color(0xFF222222), // text_black
                                fontFamily = FontFamily.Monospace // Special Elite字体
                            )
                        ) {
                            append("WhisperCove")
                        }
                    },
                    style = TextStyle(
                        fontSize = 22.sp, // text_title_size
                        fontFamily = FontFamily.Monospace, // Special Elite字体
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.drawBehind {
                        // 绘制木屋棕下划线
                        val strokeWidth = 2.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        
                        drawLine(
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            color = Color(0xFF8B6E4E), // wood_brown
                            strokeWidth = strokeWidth
                        )
                    }
                )
                
                // 右侧木屋图标
                // 这里简化为一个圆形图标，实际项目中应使用木屋图标
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = Color(0xFF6B8E5D), // tree_green
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 中部：筛选标签栏（横向滚动，邮票造型标签）
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // tag_spacing
            ) {
                items(moodTags) { mood ->
                    WhisperCoveComponents.TagButton(
                        text = mood,
                        onClick = { selectedMood = mood },
                        selected = selectedMood == mood
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp)) // section_spacing
            
            // 盲盒池（2列网格，未拆信封卡片，右上角火漆印）
            val filteredPosts = if (selectedMood == "全部") {
                posts
            } else {
                posts.filter { it.mood == selectedMood }
            }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // grid_spacing
                verticalArrangement = Arrangement.spacedBy(16.dp) // grid_spacing
            ) {
                items(filteredPosts.take(6)) { post -> // 限制显示6个盲盒
                    WhisperCoveComponents.UnopenedBlindBoxCard(
                        mood = post.mood,
                        createTime = post.timestamp,
                        onClick = { 
                            // 触发拆盲盒动效
                            unboxingCardId = post.id
                            
                            // 动画结束后导航到详情页
                            kotlinx.coroutines.GlobalScope.launch {
                                kotlinx.coroutines.delay(300) // 等待动画完成
                                navController.navigate("post/${post.id}")
                            }
                        },
                        isUnboxing = unboxingCardId == post.id
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp)) // section_spacing
            
            // 印刷分割线
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // divider_height
                    .background(Color(0xFFE0E0E0)) // divider_gray
                    .drawBehind {
                        // 添加印刷风格的虚线效果
                        val dashWidth = 8.dp.toPx()
                        val dashGap = 4.dp.toPx()
                        var x = 0f
                        
                        while (x < size.width) {
                            drawLine(
                                start = Offset(x, 0f),
                                end = Offset((x + dashWidth).coerceAtMost(size.width), 0f),
                                color = Color(0xFFE0E0E0), // divider_gray
                                strokeWidth = 1.dp.toPx()
                            )
                            x += dashWidth + dashGap
                        }
                        
                        // 分割线中间加邮票红圆点
                        val dotSize = 12.dp.toPx() // divider_dot_size
                        drawCircle(
                            color = Color(0xFFC83E37), // stamp_red
                            radius = dotSize / 2,
                            center = Offset(size.width / 2, 0f)
                        )
                    }
            )
            
            Spacer(modifier = Modifier.height(24.dp)) // section_spacing
            
            // 底部：居中核心按钮（邮票造型"抽取盲盒"）
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                WhisperCoveComponents.StampButton(
                    text = "抽取盲盒",
                    onClick = { /* TODO: 实现抽取盲盒功能 */ }
                )
            }
        }
    }
}