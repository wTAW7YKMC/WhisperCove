package com.whispercove.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
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
import com.whispercove.app.ui.models.Connection
import com.whispercove.app.ui.models.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreen(navController: NavController) {
    val connections = remember { MockData.connections }
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("关注", "粉丝", "发现")
    
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
            // 顶部：标题"海湾连接"（居中，Special Elite字体） + 右上角信封图标（消息）
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 左侧占位，使标题居中
                Box(modifier = Modifier.size(32.dp))
                
                // 居中标题
                Text(
                    text = "海湾连接",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                
                // 右上角信封图标
                IconButton(
                    onClick = { /* TODO: Open messages */ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Mail,
                        contentDescription = "Messages",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 中部：横向标签栏（"关注"、"粉丝"、"发现"，印泥红选中状态）
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
            
            // 用户列表（头像+昵称+简介，圆形头像+右侧火漆印/关注按钮）
            when (selectedTab) {
                0 -> FollowingList(connections.filter { it.isFollowing })
                1 -> FollowersList(connections.filter { it.isFollower })
                2 -> DiscoverList(connections.filter { !it.isFollowing && !it.isFollower })
            }
        }
    }
}

@Composable
fun FollowingList(connections: List<Connection>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(connections) { connection ->
            ConnectionCard(
                connection = connection,
                onProfileClick = { /* TODO: Navigate to profile */ },
                onMessageClick = { /* TODO: Open message */ }
            )
        }
    }
}

@Composable
fun FollowersList(connections: List<Connection>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(connections) { connection ->
            ConnectionCard(
                connection = connection,
                onProfileClick = { /* TODO: Navigate to profile */ },
                onMessageClick = { /* TODO: Open message */ }
            )
        }
    }
}

@Composable
fun DiscoverList(connections: List<Connection>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(connections) { connection ->
            DiscoverCard(
                connection = connection,
                onProfileClick = { /* TODO: Navigate to profile */ },
                onFollowClick = { /* TODO: Follow user */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionCard(
    connection: Connection,
    onProfileClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProfileClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 无硬阴影，保持扁平感
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 圆形头像
            AsyncImage(
                model = connection.connectedUserAvatar,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // 昵称+简介
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = connection.connectedUserName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = connection.connectedUserBio,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Cursive, // 替代Patrick Hand字体
                        fontSize = 14.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2
                )
            }
            
            // 右侧火漆印
            PrintComponents.SealDecoration(
                size = 24f
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverCard(
    connection: Connection,
    onProfileClick: () -> Unit,
    onFollowClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProfileClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 无硬阴影，保持扁平感
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 圆形头像
            AsyncImage(
                model = connection.connectedUserAvatar,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // 昵称+简介
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = connection.connectedUserName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Monospace, // 替代Special Elite字体
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = connection.connectedUserBio,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Cursive, // 替代Patrick Hand字体
                        fontSize = 14.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2
                )
            }
            
            // 关注按钮
            PrintComponents.SecondaryButton(
                text = "关注",
                onClick = onFollowClick
            )
        }
    }
}