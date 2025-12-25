package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.whispercove.app.ui.components.PrintComponents
import com.whispercove.app.ui.models.MockData
import com.whispercove.app.ui.models.Letter

@Composable
fun ProfileScreen(navController: NavController) {
    val userProfile = remember { MockData.userProfile }
    val userLetters = remember { MockData.letters.filter { it.authorId == "current_user" } }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // 顶部：封面图（占位） + 右上角设置图标 + 波浪装饰
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
            ) {
                // 设置图标
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 28.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(
                        onClick = { /* TODO: Open settings */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // 波浪装饰
                PrintComponents.WaveDecoration(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
            
            // 中部：个人信息（圆形头像+昵称+用户名+简介） + 统计数据（作品/关注/粉丝） + 编辑资料按钮 + 横向标签栏
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    // 圆形头像
                    AsyncImage(
                        model = userProfile.avatar,
                        contentDescription = "Profile Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = userProfile.name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = userProfile.bio,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Cursive, // 替代Patrick Hand字体
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // 统计数据（已发送/已接收/已回复）
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("已发送", "${userProfile.totalLettersSent}")
                    StatItem("已接收", "${userProfile.totalLettersReceived}")
                    StatItem("已回复", "${userProfile.totalReplies}")
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // 编辑资料按钮
                PrintComponents.SecondaryButton(
                    text = "编辑个人资料",
                    onClick = { /* TODO: Edit profile */ },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // 横向标签栏（"作品"、"收藏"、"喜欢"）
                var selectedTab by remember { mutableStateOf(0) }
                val tabs = listOf("作品", "收藏", "喜欢")
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        PrintComponents.TagButton(
                            text = title,
                            onClick = { selectedTab = index },
                            selected = selectedTab == index,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // 底部：内容展示区（3列网格，信件卡片+右上角火漆印）
                when (selectedTab) {
                    0 -> UserLettersGrid(userLetters)
                    1 -> Text("收藏内容", style = MaterialTheme.typography.bodyLarge)
                    2 -> Text("喜欢的内容", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )
        
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun UserLettersGrid(letters: List<Letter>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(letters) { letter ->
            Card(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable { /* TODO: Open letter */ },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 无硬阴影，保持扁平感
            ) {
                Box {
                    if (!letter.isRead) {
                        // 未拆信件显示信封图标
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📧",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 32.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    } else {
                                // 已拆信件显示内容预览
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = letter.content.take(20) + if (letter.content.length > 20) "..." else "",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = FontFamily.Cursive, // 替代Patrick Hand字体
                                    fontSize = 12.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 3
                            )
                        }
                    }
                    
                    // 右上角火漆印
                    if (letter.isRead) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(4.dp)
                        ) {
                            PrintComponents.SealDecoration(
                                size = 12f
                            )
                        }
                    }
                }
            }
        }
    }
}