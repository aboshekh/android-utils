@file:JvmName("ThemeUtils")

package com.aboshekh.androidutils

import android.content.Context

/**
 * Retrieves a color value from the current theme attributes.
 *
 * Supports both:
 * - Color resources
 * - ColorStateList resources
 *
 * If the attribute resolves to a ColorStateList, the default color
 * will be returned.
 *
 * This function is intended for internal use within the utils package.
 *
 * @param attr Theme attribute resource ID (e.g. android.R.attr.colorPrimary)
 * @return Resolved color value
 */
 fun Context.getThemeColor(attr: Int): Int {
    val typedArray = obtainStyledAttributes(intArrayOf(attr))
    val colorStateList = typedArray.getColorStateList(0)
    val color = colorStateList?.defaultColor ?: typedArray.getColor(0, 0)
    typedArray.recycle()
    return color
}
