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
fun MultiPerlinNoise() {
    var enabled by remember { mutableStateOf(false) }

    val defaultPerlin: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec(0.0)
    )

    val cycle3: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec(0.0, cycle = 3f)
    )

    val cycle4: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec(0.0, cycle = 4f)
    )

    val length5: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec(0.0, length = 5f)
    )

    val length6: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec(0.0, length = 6f)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Default")
        AnimateBoxHorizontal(defaultPerlin)
        Text("Length 5")
        AnimateBoxHorizontal(length5)
        Text("Length 6")
        AnimateBoxHorizontal(length6)
        Text("Cycle 3")
        AnimateBoxHorizontal(cycle3)
        Text("Cycle 4")
        AnimateBoxHorizontal(cycle4)
        Button(onClick = { enabled = !enabled }) {
            Text("Click Me")
        }
    }
}
