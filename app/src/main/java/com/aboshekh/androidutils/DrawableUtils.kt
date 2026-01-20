@file:JvmName("DrawableUtils")

package com.aboshekh.androidutils

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable

/**
 * Converts any [Drawable] into a mutable [Bitmap].
 *
 * If the drawable is already a [BitmapDrawable], a copy of its bitmap
 * will be returned to ensure mutability.
 *
 * @param scaleFactor Scale multiplier applied to the drawable size.
 *                    Useful when generating higher or lower resolution bitmaps.
 *
 * @return A mutable [Bitmap] representation of the drawable.
 */
fun Drawable.toBitmap(scaleFactor: Float = 1.0f): Bitmap {
    if (this is BitmapDrawable) {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }

    val width = (intrinsicWidth * scaleFactor).toInt().coerceAtLeast(1)
    val height = (intrinsicHeight * scaleFactor).toInt().coerceAtLeast(1)

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}


/**
 * Builder class for creating customizable [Drawable] objects based on [GradientDrawable].
 *
 * Supports:
 * - Solid colors
 * - Gradients
 * - Individual corner radii
 * - Stroke configuration
 * - Optional ripple effect
 *
 * Designed to simplify drawable creation without XML.
 *
 * @param shape The drawable shape (e.g. [GradientDrawable.RECTANGLE], [GradientDrawable.OVAL])
 */
class DrawableBuilder(private val shape: Int) {

    private var color: Int? = null
    private var gradientColors: IntArray? = null
    private var rippleColor: Int? = null
    private var gradientOrientation: GradientDrawable.Orientation? = null
    private var cornerRadii: FloatArray? = null
    private var strokeWidth: Int? = null
    private var strokeColor: Int? = null

    /**
     * Sets a solid color for the drawable.
     * Clears any previously set gradient.
     */
    fun setColor(color: Int): DrawableBuilder {
        this.color = color
        this.gradientColors = null
        return this
    }

    /**
     * Sets a uniform corner radius for all corners.
     */
    fun setCornerRadius(cornerRadius: Float): DrawableBuilder =
        setCornerRadius(cornerRadius, cornerRadius, cornerRadius, cornerRadius)

    /**
     * Sets individual corner radii for each corner.
     *
     * @param topLeftCornerRadius Radius of the top-left corner
     * @param topRightCornerRadius Radius of the top-right corner
     * @param bottomRightCornerRadius Radius of the bottom-right corner
     * @param bottomLeftCornerRadius Radius of the bottom-left corner
     */
    fun setCornerRadius(
        topLeftCornerRadius: Float,
        topRightCornerRadius: Float,
        bottomRightCornerRadius: Float,
        bottomLeftCornerRadius: Float,
    ): DrawableBuilder {
        this.cornerRadii = floatArrayOf(
            topLeftCornerRadius,
            topLeftCornerRadius,
            topRightCornerRadius,
            topRightCornerRadius,
            bottomRightCornerRadius,
            bottomRightCornerRadius,
            bottomLeftCornerRadius,
            bottomLeftCornerRadius
        )
        return this
    }

    /**
     * Sets a stroke around the drawable.
     *
     * @param strokeWidth Width of the stroke in pixels
     * @param strokeColor Color of the stroke
     */
    fun setStroke(strokeWidth: Int, strokeColor: Int?): DrawableBuilder {
        this.strokeWidth = strokeWidth
        this.strokeColor = strokeColor
        return this
    }

    /**
     * Applies a gradient fill to the drawable.
     * Clears any previously set solid color.
     *
     * @param gradientColors Array of colors used in the gradient
     * @param orientation Direction of the gradient
     */
    fun gradient(
        gradientColors: IntArray,
        orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,
    ): DrawableBuilder {
        this.gradientColors = gradientColors
        this.gradientOrientation = orientation
        color = null
        return this
    }

    /**
     * Enables ripple effect on the drawable.
     *
     * @param color Ripple color
     */
    fun setRippleColor(color: Int): DrawableBuilder {
        this.rippleColor = color
        return this
    }

    /**
     * Builds and returns the configured [Drawable].
     *
     * @return A [Drawable] instance with the applied configurations.
     */
    fun build(): Drawable {

        val baseDrawable = GradientDrawable().apply {
            shape = this@DrawableBuilder.shape

            this@DrawableBuilder.color?.let {
                setColor(it)
            }

            this@DrawableBuilder.gradientColors?.let {
                orientation = gradientOrientation
                colors = it
            }

            this@DrawableBuilder.cornerRadii?.let {
                if (it.size == 8) {
                    cornerRadii = it
                }
            }

            if (strokeWidth != null && strokeColor != null) {
                setStroke(strokeWidth!!, strokeColor!!)
            }
        }

        if (rippleColor == null) return baseDrawable

        val maskDrawable = GradientDrawable().apply {
            shape = this@DrawableBuilder.shape
            setColor(Color.WHITE)
            cornerRadii?.let {
                if (it.size == 8) {
                    cornerRadii = it
                }
            }
        }

        return RippleDrawable(
            ColorStateList.valueOf(rippleColor!!),
            baseDrawable,
            maskDrawable
        )
    }
}
