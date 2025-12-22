package com.whispercove.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whispercove.app.ui.components.PrintComponents
import com.whispercove.app.ui.theme.SealRed

@Composable
fun CreateScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf("") }
    val moodTags = listOf("喜悦", "平静", "兴奋", "思考", "期待", "焦虑")
    val remainingChars = 500 - content.length
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 顶部：返回按钮（左上角，小尺寸）+ 标题"封装情绪"（居中，Special Elite字体）
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 返回按钮
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // 居中标题
                Text(
                    text = "封装情绪",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // 右侧占位，使标题居中
                Box(modifier = Modifier.size(32.dp))
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 中部：信纸输入区（带手写横线暗纹，500字限制）
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    // 信纸横线纹理
                    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
                    
                    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        val path = Path()
                        val strokeWidth = 0.5.dp.toPx()
                        
                        for (i in 1..20) {
                            val y = i * 10.dp.toPx()
                            path.moveTo(0f, y)
                            path.lineTo(size.width, y)
                        }
                        
                        drawPath(
                            path = path,
                            color = onSurfaceColor.copy(alpha = 0.1f),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(
                                width = strokeWidth
                            )
                        )
                    }
                    
                    // 输入框
                    TextField(
                        value = content,
                        onValueChange = { 
                            if (it.length <= 500) content = it 
                        },
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "写下此刻的心情，让它在海湾中漂流...",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = FontFamily.Cursive, // 替代Patrick Hand字体
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            )
                        }
                    )
                }
            }
            
            // 字数提示
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "还可输入 $remainingChars 字",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 情绪标签选择（2×3网格）
            Text(
                text = "选择此刻的情绪",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 情绪标签网格
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 第一行
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    moodTags.take(3).forEach { mood ->
                        PrintComponents.TagButton(
                            text = mood,
                            onClick = { selectedMood = if (selectedMood == mood) "" else mood },
                            selected = selectedMood == mood,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                // 第二行
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    moodTags.drop(3).forEach { mood ->
                        PrintComponents.TagButton(
                            text = mood,
                            onClick = { selectedMood = if (selectedMood == mood) "" else mood },
                            selected = selectedMood == mood,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 图片上传按钮（次要按钮）
            PrintComponents.SecondaryButton(
                text = "添加图片",
                icon = Icons.Default.Image,
                onClick = { /* TODO: Handle image upload */ },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        // 底部：双按钮布局（左侧"返回"次要按钮，右侧"封装"主要按钮）
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 返回按钮
                PrintComponents.SecondaryButton(
                    text = "返回",
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                )
                
                // 打包盲盒按钮
                PrintComponents.StampButton(
                    text = "封装",
                    onClick = { 
                        // TODO: Handle post submission
                        navController.navigate("home")
                    }
                )
            }
        }
    }
}