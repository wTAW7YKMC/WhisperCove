package com.whispercove.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WhisperFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.padding(16.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Create"
        )
    }
}

@Composable
fun WhisperButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(text)
    }
}

@Composable
fun WhisperTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(placeholder) },
        shape = MaterialTheme.shapes.medium,
        visualTransformation = if (isPassword) 
            androidx.compose.ui.text.input.PasswordVisualTransformation() 
        else 
            androidx.compose.ui.text.input.VisualTransformation.None
    )
}

@Composable
fun WhisperAvatar(
    imageUrl: String,
    contentDescription: String,
    size: androidx.compose.ui.unit.Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.Image(
        painter = coil.compose.rememberAsyncImagePainter(imageUrl),
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .padding(4.dp),
        contentScale = androidx.compose.ui.layout.ContentScale.Crop
    )
}

@Composable
fun WhisperLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun WhisperErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("错误")
        },
        text = {
            Text(errorMessage)
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("确定")
            }
        },
        dismissButton = if (onRetry != null) {
            {
                TextButton(onClick = onRetry) {
                    Text("重试")
                }
            }
        } else null
    )
}