@file:JvmName("ScreenUtils")

package com.aboshekh.androidutils

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.os.Build
import android.util.TypedValue
import android.view.MotionEvent
import android.view.WindowInsets
import android.view.WindowManager
import android.view.WindowMetrics
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Returns the full physical screen width in pixels.
 *
 * Includes system bars (status & navigation bars).
 * Handles API level differences internally.
 */
fun Context.getFullScreenWidth(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        (getSystemService(WINDOW_SERVICE) as WindowManager).currentWindowMetrics.bounds.width()
    else
        resources.displayMetrics.widthPixels

/**
 * Returns the full physical screen height in pixels.
 *
 * Includes system bars (status & navigation bars).
 * Handles API level differences internally.
 */
fun Context.getFullScreenHeight(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        (getSystemService(WINDOW_SERVICE) as WindowManager).currentWindowMetrics.bounds.height()
    else
        resources.displayMetrics.heightPixels

/**
 * Returns the available screen width excluding system bars.
 *
 * Useful for layouts that should avoid status and navigation bars.
 */
fun Context.getAvailableScreenWidth(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

        val windowMetrics: WindowMetrics =
            (getSystemService(WINDOW_SERVICE) as WindowManager).currentWindowMetrics

        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.statusBars()
            )

        val bounds = windowMetrics.bounds
        bounds.width() - insets.left - insets.right

    } else {
        resources.displayMetrics.widthPixels
    }

/**
 * Returns the available screen height excluding system bars.
 *
 * Useful for fullscreen or gesture-based layouts.
 */
fun Context.getAvailableScreenHeight(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

        val windowMetrics: WindowMetrics =
            (getSystemService(WINDOW_SERVICE) as WindowManager).currentWindowMetrics

        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.statusBars()
            )

        val bounds = windowMetrics.bounds
        bounds.height() - insets.top - insets.bottom

    } else {
        resources.displayMetrics.heightPixels
    }

/**
 * Calculates a percentage of the available screen width.
 *
 * @param percent Value between 0f and 1f
 */
fun Context.getWidthPercentage(percent: Float): Int =
    (getAvailableScreenWidth() * percent).toInt()

/**
 * Calculates a percentage of the available screen height.
 *
 * @param percent Value between 0f and 1f
 */
fun Context.getHeightPercentage(percent: Float): Int =
    (getAvailableScreenHeight() * percent).toInt()

/**
 * Converts pixels to density-independent pixels (dp).
 */
fun Context.dpFromPx(px: Float): Float =
    px / resources.displayMetrics.density

/**
 * Converts density-independent pixels (dp) to pixels.
 */
fun Context.pxFromDp(dp: Float): Int =
    (dp * resources.displayMetrics.density).roundToInt()

/**
 * Converts scale-independent pixels (sp) to pixels.
 */
fun Context.pxFromSp(sp: Float): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        resources.displayMetrics
    ).roundToInt()

/**
 * Converts pixels to scale-independent pixels (sp).
 */
fun Context.spFromPx(px: Float): Float {
    val metrics = resources.displayMetrics
    val scaledDensity = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        1f,
        metrics
    )
    return px / scaledDensity
}

/**
 * Detects swipe direction between two [MotionEvent] points.
 *
 * Compares horizontal and vertical deltas and determines the dominant direction.
 *
 * @param e1 Initial touch event
 * @param e2 Final touch event
 * @param swipeThreshold Minimum distance (in pixels) required to count as a swipe.
 *                        Use 0f to disable threshold.
 *
 * @return Detected [SwipeDirection]
 */
fun detectSwipeDirection(
    e1: MotionEvent,
    e2: MotionEvent,
    swipeThreshold: Float = 0f,
): SwipeDirection {

    val deltaX = e2.x - e1.x
    val deltaY = e2.y - e1.y

    return if (abs(deltaX) > abs(deltaY)) {

        if (abs(deltaX) >= swipeThreshold) {
            if (deltaX > 0) SwipeDirection.RIGHT else SwipeDirection.LEFT
        } else {
            SwipeDirection.NO_SWIPE
        }

    } else {
        if (abs(deltaY) >= swipeThreshold) {
            if (deltaY > 0) SwipeDirection.DOWN else SwipeDirection.UP
        } else {
            SwipeDirection.NO_SWIPE
        }
    }
}

/**
 * Represents possible swipe directions.
 */
enum class SwipeDirection {
    NO_SWIPE,
    LEFT,
    RIGHT,
    UP,
    DOWN
}
