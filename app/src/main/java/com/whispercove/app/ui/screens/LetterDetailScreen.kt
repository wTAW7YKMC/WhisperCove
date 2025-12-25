package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
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
import com.whispercove.app.ui.components.WaxSeal
import com.whispercove.app.ui.components.WaxSealType as ComponentsWaxSealType
import com.whispercove.app.ui.models.WaxSealType as ModelsWaxSealType
import com.whispercove.app.ui.models.Letter
import com.whispercove.app.ui.models.MockData
import kotlinx.coroutines.launch

/**
 * 将DataModels中的WaxSealType转换为Components中的WaxSealType
 */
private fun convertWaxSealType(modelsType: ModelsWaxSealType): ComponentsWaxSealType {
    return when (modelsType) {
        ModelsWaxSealType.HEART -> ComponentsWaxSealType.SUN
        ModelsWaxSealType.STAR -> ComponentsWaxSealType.RAIN
        ModelsWaxSealType.FLOWER -> ComponentsWaxSealType.LEAF
        ModelsWaxSealType.MOON -> ComponentsWaxSealType.SUN
        ModelsWaxSealType.BIRD -> ComponentsWaxSealType.RAIN
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterDetailScreen(navController: NavController, letterId: String) {
    // 在实际应用中，这里应该根据letterId从数据库获取信件
    // 现在我们使用模拟数据
    val letter = remember { MockData.letters.find { it.id == letterId } ?: MockData.letters.first() }
    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(letter.likes) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "信件详情",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            isLiked = !isLiked
                            likeCount = if (isLiked) likeCount + 1 else likeCount - 1
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    if (isLiked) "已点赞" else "已取消点赞"
                                )
                            }
                        }
                    ) {
                        Icon(
                            if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "点赞",
                            tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("分享功能暂未实现")
                        }
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "分享")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F6F3)) // bg_paper
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // 发信人信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = letter.senderAvatar,
                    contentDescription = "发信人头像",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = letter.senderName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = letter.timestamp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 信件内容（使用信纸组件）
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F0E8) // 使用默认木纹信纸颜色
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = letter.content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF212121) // 使用默认文本颜色
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 信件标签信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 树洞类型标签
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = letter.treeHoleType.displayName,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                // 火漆印章
                WaxSeal(
                    sealType = convertWaxSealType(letter.waxSealType)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 心情标签
            if (letter.mood.isNotEmpty()) {
                Text(
                    text = "#${letter.mood}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 互动信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "点赞 $likeCount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    text = "回复 ${letter.replyCount}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (letter.isCollected) {
                    Text(
                        text = "已收藏",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 回复区域（占位）
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "回复区域",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "回复功能将在后续版本中实现",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}