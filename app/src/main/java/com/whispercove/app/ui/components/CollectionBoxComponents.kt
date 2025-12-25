package com.whispercove.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.*
import com.whispercove.app.ui.models.CollectionBox
import com.whispercove.app.ui.models.CollectionBoxType
import com.whispercove.app.ui.models.CollectedLetter
import com.whispercove.app.ui.models.WaxSealType
import com.whispercove.app.ui.models.getBackgroundColor
import com.whispercove.app.ui.models.getBorderColor
import com.whispercove.app.ui.models.getLidColor
import com.whispercove.app.ui.models.getTextColor
import java.text.SimpleDateFormat
import java.util.*

/**
 * 藏信盒组件 - 用于收藏功能
 * @param boxType 藏信盒类型
 * @param lettersCount 收藏信件数量
 * @param onClick 点击回调
 * @param modifier 修饰符
 */
@Composable
fun CollectionBox(
    boxType: CollectionBoxType,
    lettersCount: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 藏信盒主体
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(boxType.getBackgroundColor())
                .border(
                    width = 2.dp,
                    color = boxType.getBorderColor(),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            // 盒子盖
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(boxType.getLidColor())
                    .border(
                        width = 1.dp,
                        color = boxType.getBorderColor(),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // 盒子把手
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
            
            // 盒子主体
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(70.dp)
                    .background(boxType.getBackgroundColor())
                    .border(
                        width = 1.dp,
                        color = boxType.getBorderColor(),
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // 收藏信件数量
                Text(
                    text = lettersCount.toString(),
                    color = boxType.getTextColor(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // 藏信盒装饰
            CollectionBoxDecoration(
                boxType = boxType,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 藏信盒名称
        Text(
            text = boxType.displayName,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )
    }
}

/**
 * 藏信盒装饰组件
 */
@Composable
private fun CollectionBoxDecoration(
    boxType: CollectionBoxType,
    modifier: Modifier = Modifier
) {
    when (boxType) {
        CollectionBoxType.EMO -> {
            // emo藏信盒装饰：深绿苔藓 + "抱抱"小纸条
            Column(
                modifier = modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 苔藓效果
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                
                // "抱抱"小纸条
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "抱抱",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 8.sp
                    )
                }
            }
        }
        CollectionBoxType.FOODIE -> {
            // 干饭藏信盒装饰：迷你饭勺 + "干饭人"小贴纸
            Column(
                modifier = modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 迷你饭勺
                SpoonCanvas(
                    modifier = Modifier
                        .width(30.dp)
                        .height(20.dp),
                    primaryColor = MaterialTheme.colorScheme.primary
                )
                
                // "干饭人"小贴纸
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(2.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "干饭人",
                        color = Color.White,
                        fontSize = 8.sp
                    )
                }
            }
        }
        CollectionBoxType.STUDY -> {
            // 考研藏信盒装饰："上岸"小纸条 + 迷你笔袋
            Column(
                modifier = modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // "上岸"小纸条
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "上岸",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 8.sp
                    )
                }
                
                // 迷你笔袋
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(15.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                )
            }
        }
        CollectionBoxType.SPECIAL -> {
            // 特别收藏盒装饰：星星 + "珍藏"小贴纸
            Column(
                modifier = modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 星星
                Text(
                    text = "⭐",
                    fontSize = 20.sp
                )
                
                // "珍藏"小贴纸
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(2.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "珍藏",
                        color = Color.White,
                        fontSize = 8.sp
                    )
                }
            }
        }
    }
}

/**
 * 饭勺画布组件
 */
@Composable
private fun SpoonCanvas(
    primaryColor: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawSpoon(primaryColor)
    }
}

/**
 * 绘制饭勺
 */
private fun DrawScope.drawSpoon(primaryColor: Color) {
    // 勺柄
    drawRect(
        color = primaryColor,
        topLeft = Offset(0f, size.height * 0.4f),
        size = Size(size.width * 0.7f, size.height * 0.2f)
    )
    
    // 勺头
    drawOval(
        color = primaryColor.copy(alpha = 0.7f),
        topLeft = Offset(size.width * 0.6f, size.height * 0.2f),
        size = Size(size.width * 0.4f, size.height * 0.6f)
    )
}

/**
 * 藏信盒列表组件 - 显示所有藏信盒
 */
@Composable
fun CollectionBoxList(
    collectionBoxes: List<CollectionBox>,
    onBoxClick: (CollectionBox) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        collectionBoxes.forEach { box ->
            CollectionBox(
                boxType = box.boxType,
                lettersCount = box.letters.size,
                onClick = { onBoxClick(box) },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * 藏信盒详情组件 - 显示藏信盒中的信件列表
 */
@Composable
fun CollectionBoxDetail(
    box: CollectionBox,
    onLetterClick: (CollectedLetter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // 藏信盒标题
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 藏信盒图标
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(box.boxType.getBackgroundColor())
                    .border(
                        width = 2.dp,
                        color = box.boxType.getBorderColor(),
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = box.letters.size.toString(),
                    color = box.boxType.getTextColor(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // 藏信盒名称
            Text(
                text = box.boxType.displayName,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        // 信件列表
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(box.letters) { letter ->
                CollectedLetterItem(
                    letter = letter,
                    onClick = { onLetterClick(letter) }
                )
            }
        }
    }
}

/**
 * 收藏信件项组件
 */
@Composable
fun CollectedLetterItem(
    letter: CollectedLetter,
    onClick: () -> Unit = {}
) {
    // 格式化收藏时间
    val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())
    val collectedDate = dateFormat.format(Date(letter.collectedAt))
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 火漆印
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(getWaxSealColor(letter.letter.waxSealType))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getWaxSealIcon(letter.letter.waxSealType),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // 信件内容预览
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (letter.letter.content.length > 50) letter.letter.content.take(50) + "..." else letter.letter.content,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    maxLines = 2
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = collectedDate,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
                
                if (letter.collectionNote.isNotEmpty()) {
                    Text(
                        text = "备注: ${letter.collectionNote}",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
            
            // 收藏标记
            if (letter.letter.isCollected) {
                Text(
                    text = "⭐",
                    fontSize = 16.sp
                )
            }
        }
    }
}

/**
 * 获取火漆印章颜色
 */
private fun getWaxSealColor(waxSealType: com.whispercove.app.ui.models.WaxSealType): Color {
    return when (waxSealType) {
        com.whispercove.app.ui.models.WaxSealType.HEART -> Color(0xFFFF5252) // 红色
        com.whispercove.app.ui.models.WaxSealType.STAR -> Color(0xFFFFD740) // 金色
        com.whispercove.app.ui.models.WaxSealType.FLOWER -> Color(0xFFF48FB1) // 粉色
        com.whispercove.app.ui.models.WaxSealType.MOON -> Color(0xFF448AFF) // 蓝色
        com.whispercove.app.ui.models.WaxSealType.BIRD -> Color(0xFF69F0AE) // 绿色
    }
}

/**
 * 获取火漆印章图标
 */
private fun getWaxSealIcon(waxSealType: com.whispercove.app.ui.models.WaxSealType): String {
    return when (waxSealType) {
        com.whispercove.app.ui.models.WaxSealType.HEART -> "♥"
        com.whispercove.app.ui.models.WaxSealType.STAR -> "★"
        com.whispercove.app.ui.models.WaxSealType.FLOWER -> "✿"
        com.whispercove.app.ui.models.WaxSealType.MOON -> "☽"
        com.whispercove.app.ui.models.WaxSealType.BIRD -> "✈"
    }
}

