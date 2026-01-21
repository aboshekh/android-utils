# Android Utils

A lightweight, production-ready collection of Android utility extensions designed to simplify UI
development, screen handling, animations, and common data transformations — fully written in Kotlin.

Built with **performance**, **reusability**, and **Kotlin-first** design in mind.

---

## ✨ Features

- 🎨 Drawable creation and customization without XML
- 📐 Screen size, unit conversion, and gesture detection
- ⏱ Time and duration formatting helpers
- 🧠 String transformation utilities
- 🎨 Theme-aware color resolution
- 🖐 Swipe and gesture detection
- 🧱 Advanced View utilities with animated corner radius

---

## ⚙ Setup

1️⃣Add it in your root settings.gradle at the end of repositories (If it hasn't been added yet)
````kotlin
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
````

2️⃣ implementation
````kotlin
implementation 'com.github.aboshekh:android-utils:v1.0.0'
````
## 📦 Modules Overview

### 🎨 DrawableUtils
Programmatically create and manipulate drawables.
```kotlin
// Building Drawable Programmatically
val drawable = DrawableBuilder(GradientDrawable.RECTANGLE)
    .setColor(Color.BLUE)
    .setCornerRadius(24f)
    .setRippleColor(Color.LTGRAY)
    .build()

val bitmap: Bitmap = drawable.toBitmap()
````
    
### 🔢 NumbersUtils
Time and duration formatting helpers (milliseconds-based).
```kotlin
125000L.formatToTime()  // 02:05
````

### 📐 ScreenUtils
Screen metrics, unit conversion, and gesture detection utilities.
```kotlin

val fullScreenWidth: Int = getFullScreenWidth()
val fullScreenHeight: Int = getFullScreenHeight()

val availableScreenWidth: Int = getAvailableScreenWidth()
val availableScreenHeight: Int = getAvailableScreenHeight()

val halfScreenWidth: Int = getWidthPercentage(percent = 0.5f)
val halfScreenHeight: Int = getHeightPercentage(percent = 0.5f)

val dp: Int = context.dpFromPx(px = 50)
val px: Int = context.pxFromDp(dp = 16f)
val px: Int = context.pxFromSp(sp = 12f)
val sp: Int = context.spFromPx(px = 50)

// All the above dimensions in pixels


// swipeDirection ex
val gestureListener =
    object : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float,
        ): Boolean {

            val swipeDirection = detectSwipeDirection(e1, e2, SWIPE_THRESHOLD)

            when (swipeDirection) {
                SwipeDirection.NO_SWIPE -> noSwipeAction()
                SwipeDirection.LEFT -> leftSwipeAction()
                SwipeDirection.RIGHT -> rightSwipeAction()
                SwipeDirection.UP -> upSwipeAction()
                SwipeDirection.DOWN -> downSwipeAction()
            }

            return true
        }


    }

val gestureDetector = GestureDetector(context, gestureListener)

view.setOnTouchListener {
        _, event ->
    gestureDetector.onTouchEvent(event)
    return@setOnTouchListener true
}
```

### 🧠 StringUtils
String transformation helpers.
```kotlin
"Test String".lowercaseWithSeparator(separator = "_") // -> test_string

"MyClassName".pascalToSnakeCase() // -> "my_class_name"

"myClassName".camelToSnakeCase() // -> "my_class_name"
````

### 🎨 ThemeUtils
Theme-aware color resolution utilities.
```kotlin
val primaryColor = context.getThemeColor(android.R.attr.colorPrimary)

val adaptiveTextColor = context.getThemeColor(android.R.attr.colorControlNormal)
````

### 🧱 ViewUtils
Advanced View utilities focused on performance.
```kotlin
view.setCornerRadius(
    radius = 32f,
    duration = 300,
    animate = true
)
````


## 🧠 Design Philosophy
Kotlin Extensions over inheritance

Minimal allocations

XML-free where possible

RecyclerView-safe

Hardware-accelerated UI operations


## 📄 License

Copyright (c) 2026 Hassan Abdelaal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## 💬 Contact

For questions, suggestions, or collaboration:

📧 **Email:** h.abdelaal.dev@gmail.com


