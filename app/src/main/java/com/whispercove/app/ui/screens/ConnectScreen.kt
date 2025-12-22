package com.whispercove.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.whispercove.app.ui.models.Connection
import com.whispercove.app.ui.models.MockData

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ConnectScreen(navController: NavController) {
    val connections = remember { MockData.connections }
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("关注", "粉丝", "发现")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "连接",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tabs
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Content based on selected tab
        when (selectedTab) {
            0 -> FollowingList(connections.filter { it.isFollowing })
            1 -> FollowersList(connections.filter { it.isFollower })
            2 -> DiscoverList(connections.filter { !it.isFollowing && !it.isFollower })
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
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = onProfileClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = connection.avatarUrl,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = connection.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Text(
                    text = connection.bio,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2
                )
            }
            
            IconButton(onClick = onMessageClick) {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = "Message",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
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
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = onProfileClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = connection.avatarUrl,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = connection.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Text(
                    text = connection.bio,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2
                )
            }
            
            Button(
                onClick = onFollowClick,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("关注")
            }
        }
    }
}