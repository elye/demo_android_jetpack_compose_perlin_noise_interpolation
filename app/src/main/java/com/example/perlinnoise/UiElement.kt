package com.example.perlinnoise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object MainDestinations {
    const val MAIN_SCREEN = "mainScreen"
    const val CHILD_SCREEN = "childScreen"
    const val CHILD_APP_TYPE = "childAppType"
}

fun switch(enabled: Boolean) = if (enabled) (WIDTH - BOX_SIZE).dp else 0.dp

@Composable
fun AnimateBoxHorizontal(dbAnimate: Dp,
                         boxSize: Dp = BOX_SIZE.dp,
                         width: Dp = WIDTH.dp
) {
    Box(
        modifier = Modifier
            .height(boxSize)
            .width(width)
            .background(Color.Yellow)
    ) {
        Box(
            modifier = Modifier
                .offset(dbAnimate, 0.dp)
                .size(boxSize)
                .background(Color.Red)
        )
    }
    Spacer(modifier = Modifier.height((boxSize/2)))
}

