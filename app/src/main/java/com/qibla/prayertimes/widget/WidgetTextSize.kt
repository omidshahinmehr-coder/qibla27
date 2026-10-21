package com.qibla.prayertimes.widget

import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.glance.LocalContext

/**
 * Widget text must not change size when the user changes Android's "Font size" setting (that
 * would overflow the fixed-size cells). Glance only accepts `sp` text sizes, which scale with the
 * system font scale — so this converts a size meant as "dp" into the `sp` value that renders at
 * exactly that many dp at the *current* font scale (the scale cancels out).
 */
fun Context.fixedSp(size: Float): TextUnit {
    val metrics = resources.displayMetrics
    val px = size * metrics.density
    val sp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_SP, px, metrics)
    } else {
        @Suppress("DEPRECATION")
        val scaled = if (metrics.scaledDensity > 0f) metrics.scaledDensity else metrics.density
        px / scaled
    }
    return sp.sp
}

/** Composable shortcut for [fixedSp] using the Glance context. */
@Composable
fun fixedSp(size: Float): TextUnit = LocalContext.current.fixedSp(size)
