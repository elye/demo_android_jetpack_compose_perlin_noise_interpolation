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
import androidx.compose.ui.unit.dp

@Composable
fun SmallPerlinInterpolator() {
    var enabled by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(25) {
            val perlinNoiseAnimator: Dp by animateDpAsState(
                targetValue = smallSwitch(enabled),
                animationSpec = perlinNoiseAnimationSpec(value + 0.02 * it))
            SmallAnimateBoxHorizontal(perlinNoiseAnimator)
        }

        Button(onClick = {
            value = (0..1000).random().toDouble()

            enabled = !enabled }
        ) {
            Text("Click Me")
        }
    }
}

private fun smallSwitch(enabled: Boolean) = if (enabled) (WIDTH - SMALL_BOX_SIZE).dp else 0.dp

@Composable
private fun SmallAnimateBoxHorizontal(dbAnimate: Dp) {
    AnimateBoxHorizontal(dbAnimate, SMALL_BOX_SIZE.dp)
}
