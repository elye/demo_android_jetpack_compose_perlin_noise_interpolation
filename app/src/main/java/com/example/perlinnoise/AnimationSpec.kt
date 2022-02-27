package com.example.perlinnoise

import android.animation.TimeInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.Dp
import com.example.perlinnoise.math.noise1D
import kotlin.math.sin


fun circularSpringAnimationSpec(): TweenSpec<Dp> =
    tween(
        durationMillis = 3000,
        easing = CircularSpringInterpolatorEasing(10f)
    )

fun perlinNoiseAnimationSpec(seed: Double): TweenSpec<Dp> {
    return tween(
        durationMillis = 3000,
        easing = PerlinNoiseInterpolator(seed).toEasing()
    )
}

fun tweenAnimationSpec(): TweenSpec<Dp> =
    tween(
        durationMillis = 3000,
        easing = LinearOutSlowInEasing
    )

fun hesitatedAnimationSpec(): TweenSpec<Dp> =
    tween(
        durationMillis = 3000,
        easing = HesitateEasing
    )

fun springAnimationSpec(): SpringSpec<Dp> =
    spring(stiffness =20f, dampingRatio = 0.25f)

fun bounceAnimationSpec(): TweenSpec<Dp> =
    tween(
        durationMillis = 3000,
        easing = BounceInterpolator().toEasing()
    )

val HesitateEasing = CubicBezierEasing(0f, 1f, 1f, 0f)

fun CircularSpringInterpolatorEasing(tension: Float = 50f): Easing =
    CircularSpringInterpolator(tension).toEasing()

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }

class CircularSpringInterpolator(private val tension: Float = 50f) : Interpolator {
    override fun getInterpolation(input: Float): Float {
        return (sin(tension * input) * sin(Math.PI * input) + input).toFloat()
    }
}

class PerlinNoiseInterpolator(
    private val seed: Double,
    private val cycle: Int = 2,
    private val length: Int = 2,
    private val noiseWeight: Int = 2
) : Interpolator {
    override fun getInterpolation(input: Float): Float {
        val noiseStrength = (if (input < 0.5) input else (1f - input)) * noiseWeight
        return (noise1D((seed + input.toDouble()) * cycle)).toFloat() * length * noiseStrength + input
    }
}
