package com.example.perlinnoise

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun PerlinInterpolator() {
    var enabled by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(10) {
            val perlinNoiseAnimator: Dp by animateDpAsState(
                targetValue = switch(enabled),
                animationSpec = perlinNoiseAnimationSpec(value + 0.02 * it))
            AnimateBoxHorizontal(perlinNoiseAnimator)
        }

        Button(onClick = {
            value = (0..1000).random().toDouble()

            enabled = !enabled }
        ) {
            Text("Click Me")
        }
    }
}
