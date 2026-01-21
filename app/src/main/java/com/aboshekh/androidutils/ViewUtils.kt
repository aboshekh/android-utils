@file:JvmName("ViewUtils")

package com.aboshekh.androidutils

import android.animation.ValueAnimator
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.core.animation.doOnEnd

/**
 * Sets a rounded corner radius on a [View] using [ViewOutlineProvider].
 *
 * Supports both instant and animated radius changes.
 * The current radius and animator state are stored internally using view tags.
 *
 * This approach avoids creating custom drawables and works efficiently
 * with hardware-accelerated clipping.
 *
 * @param radius Target corner radius in pixels
 * @param duration Animation duration in milliseconds (only if animate = true)
 * @param animate Whether the radius change should be animated
 */
fun View.setCornerRadius(
    radius: Float,
    duration: Long = 500L,
    animate: Boolean = false
) {

    // Initialize outline provider only once
    if (getTag(R.id.tag_is_outline_set) as? Int != 1) {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(
                    0,
                    0,
                    view.width,
                    view.height,
                    view.getRadius()
                )
            }
        }
    }

    clipToOutline = true
    setTag(R.id.tag_is_outline_set, 1)

    val startRadius = getRadius()

    // Cancel any running animation
    getAnimator()?.cancel()

    if (!animate) {
        setRadius(radius)
        invalidate()
        requestLayout()
        return
    }

    ValueAnimator.ofFloat(0f, 1f).apply {
        this.duration = duration

        addUpdateListener { anim ->
            val progress = anim.animatedFraction
            setRadius(startRadius + (radius - startRadius) * progress)
            invalidate()

            if (progress == 1f) {
                requestLayout()
            }
        }

        doOnEnd {
            setAnimator(null)
        }

        setAnimator(this)
        start()
    }
}

/**
 * Retrieves the currently applied corner radius from the view tag.
 */
private fun View.getRadius(): Float =
    (getTag(R.id.tag_radius) as? Float) ?: 0f

/**
 * Stores the corner radius in the view tag.
 */
private fun View.setRadius(radius: Float) {
    setTag(R.id.tag_radius, radius)
}

/**
 * Retrieves the active corner radius animator from the view tag.
 */
private fun View.getAnimator(): ValueAnimator? =
    getTag(R.id.tag_radius_animator) as? ValueAnimator

/**
 * Stores the active corner radius animator in the view tag.
 */
private fun View.setAnimator(animator: ValueAnimator?) {
    setTag(R.id.tag_radius_animator, animator)
}
