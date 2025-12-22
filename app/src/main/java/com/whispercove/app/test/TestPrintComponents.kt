package com.whispercove.app.test

import androidx.compose.runtime.Composable
import com.whispercove.app.ui.components.PrintComponents

@Composable
fun TestPrintComponents() {
    PrintComponents.StampButton(
        text = "Test",
        onClick = { }
    )
}