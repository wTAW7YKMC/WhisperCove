package com.whispercove.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.whispercove.app.ui.components.PrintComponents
import com.whispercove.app.ui.models.WhisperPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhisperCard(
    post: WhisperPost,
    onPostClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // 根据帖子类型确定卡片样式
    val cardColor = if (post.isOpened) {
        MaterialTheme.colorScheme.surface // 已拆盲盒：米白底色 + 信纸纹理
    } else {
        Color(0xFFE0D5C7) // 未拆盲盒：浅棕底色 + 牛皮纸纹理
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // 无硬阴影，保持扁平感
        onClick = onPostClick
    ) {
        Box {
            // 已拆盲盒添加邮票装饰
            if (post.isOpened) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                ) {
                    PrintComponents.SealDecoration(size = 32f)
                }
            }
            
            // 未拆盲盒添加信封封口图案
            if (!post.isOpened) {
                val primaryColor = MaterialTheme.colorScheme.primary
                
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                ) {
                    val path = Path().apply {
                        moveTo(size.width / 2 - 30.dp.toPx(), 0f)
                        lineTo(size.width / 2, 25.dp.toPx())
                        lineTo(size.width / 2 + 30.dp.toPx(), 0f)
                        close()
                    }
                    
                    drawPath(
                        path = path,
                        color = primaryColor.copy(alpha = 0.3f)
                    )
                }
            }
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // User info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = post.userAvatar,
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Column {
                        Text(
                            text = post.userName,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal // 手写风格不需要加粗
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        Text(
                            text = post.timestamp,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Post content
                if (post.isOpened) {
                    Text(
                        text = post.content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 3 // 最多显示3行预览
                    )
                } else {
                    // 未拆盲盒显示提示文字
                    Text(
                        text = "来自海湾的神秘信件...",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
                
                if (post.isOpened && post.imageUrl.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    AsyncImage(
                        model = post.imageUrl,
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // 情绪标签（已拆盲盒显示）
                if (post.isOpened && post.mood.isNotEmpty()) {
                    PrintComponents.TagButton(
                        text = post.mood,
                        onClick = { /* TODO: Filter by mood */ },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        IconButton(onClick = { /* TODO: Like post */ }) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Like",
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                        
                        Text(
                            text = "${post.likes}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    
                    IconButton(onClick = { /* TODO: Comment */ }) {
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = "Comment",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    
                    IconButton(onClick = { /* TODO: Share */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}