package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.whispercove.app.ui.components.PrintComponents
import com.whispercove.app.ui.models.TreeHole
import com.whispercove.app.ui.models.DriftingBottle
import com.whispercove.app.ui.models.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navController: NavController) {
    val treeHoles = remember { MockData.treeHoles }
    val driftingBottles = remember { MockData.driftingBottles }
    var selectedCategory by remember { mutableStateOf("全部") }
    val categories = listOf("全部", "树洞", "漂流瓶")
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            // 顶部：标题"探索发现"（居中，Special Elite字体） + 搜索图标
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 左侧占位，使标题居中
                Box(modifier = Modifier.size(32.dp))
                
                // 居中标题
                Text(
                    text = "探索发现",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                
                // 右侧搜索图标
                IconButton(
                    onClick = { /* TODO: Open search */ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 中部：分类标签栏（横向滚动，印泥红矩形标签）
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categories.forEach { category ->
                    PrintComponents.TagButton(
                        text = category,
                        onClick = { selectedCategory = category },
                        selected = selectedCategory == category
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 探索内容区（树洞和漂流瓶）
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp) // 为底部导航预留空间
            ) {
                // 显示树洞
                if (selectedCategory == "全部" || selectedCategory == "树洞") {
                    items(treeHoles) { treeHole ->
                        TreeHoleCard(
                            treeHole = treeHole,
                            onItemClick = { /* TODO: Navigate to tree hole details */ }
                        )
                    }
                }
                
                // 显示漂流瓶
                if (selectedCategory == "全部" || selectedCategory == "漂流瓶") {
                    items(driftingBottles) { bottle ->
                        DriftingBottleCard(
                            bottle = bottle,
                            onItemClick = { /* TODO: Navigate to bottle details */ }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeHoleCard(
    treeHole: TreeHole,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 无硬阴影，保持扁平感
    ) {
        Box {
            // 背景图片
            AsyncImage(
                model = treeHole.imageUrl,
                contentDescription = treeHole.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // 半透明遮罩
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    )
            )
            
            // 内容区域
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 顶部：树洞图标
                Text(
                    text = "🌳",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 24.sp
                )
                
                // 底部：名称和位置
                Column {
                    Text(
                        text = treeHole.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Monospace, // Special Elite风格
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = treeHole.location,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.Cursive, // Patrick Hand风格
                            fontSize = 14.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        maxLines = 1
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "${treeHole.letterCount}封信",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        maxLines = 1
                    )
                }
            }
            
            // 右上角火漆印装饰
            PrintComponents.SealDecoration(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriftingBottleCard(
    bottle: DriftingBottle,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 无硬阴影，保持扁平感
    ) {
        Box {
            // 背景渐变（海洋效果）
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        )
                    )
            )
            
            // 内容区域
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // 漂流瓶图标
                Text(
                    text = "🍾",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = if (bottle.isPickedUp) "已被捡起" else "漂流中",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Cursive, // Patrick Hand风格
                        fontSize = 14.sp
                    ),
                    color = if (bottle.isPickedUp) 
                        MaterialTheme.colorScheme.error 
                    else 
                        MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "来自${bottle.origin}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                
                Text(
                    text = "漂流${bottle.journeyDays}天",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            
            // 右上角小火漆印装饰
            PrintComponents.SealDecoration(
                size = 24f,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}