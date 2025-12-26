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
import com.whispercove.app.ui.components.TreeHoleComponents
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val letters = MockData.letters
    val moodTags = listOf("全部", "考研emo", "干饭快乐", "平静", "兴奋", "思考", "期待")
    var selectedMood by remember { mutableStateOf("全部") }
    var unboxingCardId by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // 使用主题背景色
            // 添加极淡的树洞轮廓暗纹
            .drawBehind {
                // 绘制极淡的树洞轮廓暗纹
                val lineColor = Color(0xFF6B8E5D).copy(alpha = 0.05f) // 使用固定颜色代替MaterialTheme
                
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
                                color = MaterialTheme.colorScheme.onBackground, // 使用主题文本颜色
                                fontFamily = FontFamily.Monospace // Special Elite字体
                            )
                        ) {
                            append("树洞信件漂流站")
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
                            color = Color(0xFF8D6E63), // 使用固定颜色代替MaterialTheme
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
                            color = MaterialTheme.colorScheme.primary, // 使用主题色
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
                    TreeHoleComponents.TagButton(
                        text = mood,
                        onClick = { selectedMood = mood },
                        selected = selectedMood == mood
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp)) // section_spacing
            
            // 信件池（2列网格，未拆信封卡片，右上角火漆印）
            val filteredLetters = if (selectedMood == "全部") {
                letters
            } else {
                letters.filter { it.mood == selectedMood }
            }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // grid_spacing
                verticalArrangement = Arrangement.spacedBy(16.dp) // grid_spacing
            ) {
                items(filteredLetters.take(6)) { letter ->
                    TreeHoleComponents.UnopenedLetterCard(
                        mood = letter.mood,
                        createTime = letter.timestamp,
                        onClick = {
                            unboxingCardId = letter.id
                            scope.launch {
                                try {
                                    kotlinx.coroutines.delay(300)
                                    navController.navigate("letter/${letter.id}")
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    // 如果导航失败，重置unboxingCardId
                                    unboxingCardId = null
                                }
                            }
                        },
                        isUnboxing = unboxingCardId == letter.id
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
                TreeHoleComponents.StampButton(
                    text = "抽取信件",
                    onClick = { 
                        scope.launch {
                            try {
                                val sealedLetters = letters.filter { it.isSealed }
                                if (sealedLetters.isNotEmpty()) {
                                    val randomLetter = sealedLetters.random()
                                    unboxingCardId = randomLetter.id
                                    kotlinx.coroutines.delay(300)
                                    navController.navigate("letter/${randomLetter.id}")
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                unboxingCardId = null
                            }
                        }
                    }
                )
            }
        }
    }
}