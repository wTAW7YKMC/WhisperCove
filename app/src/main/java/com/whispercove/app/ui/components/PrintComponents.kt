package com.whispercove.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whispercove.app.ui.theme.SealRed

object PrintComponents {
    
    // 邮票造型核心按钮（56dp×56dp）
    @Composable
    fun StampButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true
    ) {
        val buttonColor = if (enabled) MaterialTheme.colorScheme.primary else Color.Gray
        val borderColor = if (enabled) SealRed else Color.Gray
        val borderWidth = if (enabled) 2.dp else 1.dp
        
        Box(
            modifier = modifier
                .size(56.dp)
                .clickable(enabled = enabled) { onClick() }
                .background(color = buttonColor)
                .border(width = borderWidth, color = borderColor)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 12.sp
            )
        }
    }

    // 次要按钮（矩形，圆角4dp）
    @Composable
    fun SecondaryButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        icon: ImageVector? = null
    ) {
        val backgroundColor = if (enabled) MaterialTheme.colorScheme.tertiary else Color.Gray
        val borderColor = MaterialTheme.colorScheme.primary
        
        Button(
            onClick = onClick,
            modifier = modifier
                .width(100.dp)
                .height(36.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            ),
            border = BorderStroke(1.dp, borderColor),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }

    // 标签按钮（无圆角，印泥红填充）
    @Composable
    fun TagButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        selected: Boolean = false
    ) {
        val backgroundColor = if (selected) SealRed else MaterialTheme.colorScheme.tertiary
        val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
        
        Button(
            onClick = onClick,
            modifier = modifier.wrapContentWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            ),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

    // 火漆印装饰
    @Composable
    fun SealDecoration(
        modifier: Modifier = Modifier,
        size: Float = 24f,
        color: Color = SealRed
    ) {
        Box(
            modifier = modifier
                .size(size.dp, size.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(50)
                )
        )
    }

    // 波浪装饰
    @Composable
    fun WaveDecoration(
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
                )
        )
    }
}