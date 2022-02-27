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
fun MultiInterpolator() {
    var enabled by remember { mutableStateOf(false) }

    val perlinNoiseAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = perlinNoiseAnimationSpec((0..1000).random().toDouble())
    )

    val circularSpringAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = circularSpringAnimationSpec()
    )

    val hesitateAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = hesitatedAnimationSpec()
    )

    val tweenAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = tweenAnimationSpec()
    )

    val springAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = springAnimationSpec()
    )

    val bounceAnimate: Dp by animateDpAsState(
        targetValue = switch(enabled),
        animationSpec = bounceAnimationSpec()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Tween")
        AnimateBoxHorizontal(tweenAnimate)
        Text("Bounce")
        AnimateBoxHorizontal(bounceAnimate)
        Text("Spring")
        AnimateBoxHorizontal(springAnimate)
        Text("Hesitate")
        AnimateBoxHorizontal(hesitateAnimate)
        Text("Circular Spring")
        AnimateBoxHorizontal(circularSpringAnimate)
        Text("Perlin Noise")
        AnimateBoxHorizontal(perlinNoiseAnimate)
        Button(onClick = { enabled = !enabled }) {
            Text("Click Me")
        }
    }
}
