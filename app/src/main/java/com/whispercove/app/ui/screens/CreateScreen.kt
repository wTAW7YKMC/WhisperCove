package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whispercove.app.ui.components.WhisperCoveComponents
import kotlinx.coroutines.launch

@Composable
fun CreateScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf("") }
    var isPacking by remember { mutableStateOf(false) } // 是否正在打包盲盒
    val moodTags = listOf("考研emo", "干饭快乐", "平静", "兴奋", "思考", "期待")
    val remainingChars = 200 - content.length
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6F3)) // bg_paper
            // 添加极淡的树洞轮廓暗纹
            .drawBehind {
                // 绘制极淡的树洞轮廓暗纹
                val lineColor = Color(0xFF6B8E5D).copy(alpha = 0.05f) // tree_green with 5% opacity
                
                // 绘制树洞轮廓
                val centerX = size.width * 0.2f
                val centerY = size.height * 0.3f
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp) // section_spacing
        ) {
            // 顶部：返回按钮（左上角，次要按钮）+ 标题"投递信件"（居中，Special Elite字体）
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 返回按钮
                WhisperCoveComponents.TagButton(
                    text = "返回",
                    onClick = { navController.popBackStack() },
                    selected = false
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // 居中标题
                Text(
                    text = "投递信件",
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace, // Special Elite字体
                        fontSize = 18.sp, // text_section_title_size
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF222222) // text_black
                    )
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // 右侧占位，使标题居中
                Box(modifier = Modifier.size(60.dp, 32.dp))
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 中部：盲盒输入框（木屋质感）
            WhisperCoveComponents.WoodHouseInputField(
                value = content,
                onValueChange = { content = it },
                placeholder = "写下此刻的心情，让它在树洞中漂流...",
                isResponseInput = false,
                maxLength = 200,
                minHeight = 80f, // input_min_height_blindbox
                isPacking = isPacking
            )
            
            Spacer(modifier = Modifier.height(20.dp)) // 输入框和标签区间距
            
            // 情绪标签选择（2行4列，邮票标签）
            Text(
                text = "选择此刻的情绪",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace, // Special Elite字体
                    fontSize = 16.sp, // text_content_size
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF222222) // text_black
                )
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 情绪标签网格
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 第一行
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // tag_spacing
                ) {
                    moodTags.take(3).forEach { mood ->
                        WhisperCoveComponents.TagButton(
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
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // tag_spacing
                ) {
                    moodTags.drop(3).forEach { mood ->
                        WhisperCoveComponents.TagButton(
                            text = mood,
                            onClick = { selectedMood = if (selectedMood == mood) "" else mood },
                            selected = selectedMood == mood,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp)) // section_spacing
        }
        
        // 页面右下角添加微型木屋图标
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            // 这里简化为一个圆形图标，实际项目中应使用木屋图标
            Box(
                modifier = Modifier
                    .size(24.dp) // cabin_icon_size
                    .background(
                        color = Color(0xFF8B6E4E), // wood_brown
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
        }
        
        // 底部：右侧核心按钮（邮票造型"打包盲盒"）+ 左侧字数提示
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 左侧字数提示
                Text(
                    text = "${content.length}/200",
                    style = TextStyle(
                        fontFamily = FontFamily.Default, // 思源黑体
                        fontSize = 12.sp, // text_caption_size
                        color = Color(0xFFC83E37) // stamp_red
                    )
                )
                
                // 右侧核心按钮
                WhisperCoveComponents.StampButton(
                    text = "打包盲盒",
                    onClick = { 
                        // 触发打包盲盒动效
                        isPacking = true
                        
                        // 动画结束后导航回首页
                        kotlinx.coroutines.GlobalScope.launch {
                            kotlinx.coroutines.delay(200) // 等待动画完成
                            navController.navigate("home")
                        }
                    }
                )
            }
        }
    }
}