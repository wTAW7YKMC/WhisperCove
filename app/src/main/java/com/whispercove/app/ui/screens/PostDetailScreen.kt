package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whispercove.app.ui.components.TreeHoleComponents
import com.whispercove.app.ui.theme.*
import com.whispercove.app.ui.models.Letter
import com.whispercove.app.ui.models.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    navController: NavController,
    letterId: String
) {
    // 查找对应的信件数据
    val letter = remember { MockData.letters.find { it.id == letterId } ?: MockData.letters.first() }
    var responseContent by remember { mutableStateOf("") }
    val remainingChars = 200 - responseContent.length
    
    // 页面背景：bg_paper + 树洞暗纹
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6F3)) // bg_paper
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 28.dp), // 页面边距
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 顶部导航区
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "返回",
                        tint = Color(0xFF6B8E5D) // tree_green
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = "信件详情",
                    color = Color(0xFF222222), // text_black
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif, // Special Elite
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
            
            // 已拆信件卡片（信件 + 树洞风格）
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color(0xFFF9F6F3)) // bg_paper
                    .border(
                        width = 1.dp,
                        color = Color(0xFFA68A69), // wood_brown_light
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp)
            ) {
                Column {
                    // 左上角邮票造型小标签
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        // 邮票标签
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    color = Color(0xFF8B6E4E), // wood_brown
                                    shape = MaterialTheme.shapes.extraSmall
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFC83E37), // stamp_red
                                    shape = MaterialTheme.shapes.extraSmall
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "信",
                                color = Color(0xFFF9F6F3), // bg_paper
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Serif // Special Elite
                            )
                        }
                        
                        // 创建时间
                        Text(
                            text = letter.timestamp,
                            color = Color(0xFF666666), // text_gray
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Default // 思源黑体
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 信件内容
                    Text(
                        text = letter.content,
                        color = Color(0xFF222222), // text_black
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif, // Special Elite
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 情绪标签
                    Text(
                        text = "#${letter.mood}",
                        color = Color(0xFFC83E37), // stamp_red
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Serif, // Special Elite
                        modifier = Modifier
                            .background(
                                color = Color(0xFFC83E37).copy(alpha = 0.1f), // stamp_red with 10% opacity
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            // 分割线（中间加stamp_red圆点）
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFE0E0E0)) // divider_gray
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(Color(0xFFC83E37), shape = CircleShape) // stamp_red
                        .align(Alignment.Center)
                )
            }
            
            // 回应区标题
            Text(
                text = "写下你的回应",
                color = Color(0xFF222222), // text_black
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif, // Special Elite
                modifier = Modifier.padding(vertical = 8.dp)
            )
            
            // 回应输入框（木屋质感）
            TreeHoleComponents.WoodHouseInputField(
                value = responseContent,
                onValueChange = { newValue -> responseContent = newValue },
                placeholder = "写下你的回应，让温暖传递...",
                isResponseInput = true,
                maxLength = 200,
                minHeight = 60f // input_min_height_response
            )
            
            // 底部操作区
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 字数提示
                Text(
                    text = "剩余字数: $remainingChars",
                    color = Color(0xFFC83E37), // stamp_red
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default // 思源黑体
                )
                
                // 发送回应按钮
                TreeHoleComponents.StampButton(
                    text = "发送回应",
                    onClick = {
                        // TODO: 实现发送回应逻辑
                        navController.popBackStack()
                    }
                )
            }
            
            // 页面右下角装饰：微型木屋图标
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                // 这里可以添加木屋图标，暂时用文字代替
                Text(
                    text = "🏠",
                    fontSize = 24.sp,
                    color = Color(0xFF8B6E4E) // wood_brown
                )
            }
        }
    }
}