package com.whispercove.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.whispercove.app.ui.models.ExploreItem
import com.whispercove.app.ui.models.MockData

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ExploreScreen(navController: NavController) {
    val exploreItems = remember { MockData.exploreItems }
    var selectedCategory by remember { mutableStateOf("全部") }
    val categories = listOf("全部", "音乐", "艺术", "摄影", "文学", "生活")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "探索",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO: Handle search */ },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("搜索感兴趣的内容...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            shape = RoundedCornerShape(28.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Categories
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(120.dp)
        ) {
            items(categories) { category ->
                @OptIn(ExperimentalMaterial3Api::class)
                FilterChip(
                    onClick = { selectedCategory = category },
                    label = { Text(category) },
                    selected = selectedCategory == category
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Explore items grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(exploreItems.filter { 
                selectedCategory == "全部" || it.category == selectedCategory 
            }) { item ->
                ExploreItemCard(
                    item = item,
                    onItemClick = { /* TODO: Navigate to item details */ }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreItemCard(
    item: ExploreItem,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        onClick = onItemClick
    ) {
        Box {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    
                    Text(
                        text = item.author,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}