# Android Utils

A lightweight, production-ready collection of Android utility extensions designed to simplify UI development, screen handling, animations, and common data transformations — fully written in Kotlin.

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

## 📦 Modules Overview

### 🎨 DrawableUtils
Programmatically create and manipulate drawables.

```kotlin

val drawable = DrawableBuilder(GradientDrawable.RECTANGLE)
    .setColor(Color.BLUE)
    .setCornerRadius(24f)
    .setRippleColor(Color.LTGRAY)
    .build()
    
    
🔢 NumbersUtils
Time and duration formatting helpers (milliseconds-based).


125000L.formatToTime()                // 02:05
7200000L.formatToDuration(context)    // 02 h


📐 ScreenUtils
Screen metrics, unit conversion, and gesture detection utilities.


val width = context.getWidthPercentage(0.8f)
val swipe = detectSwipeDirection(e1, e2, 50f)


🧠 StringUtils
String transformation helpers.


"MyClassName".pascalToSnakeCase() // my_class_name
"MyClassName".camelToSnakeCase()  // my_class_name


🎨 ThemeUtils
Theme-aware color resolution utilities.

val primaryColor = context.getThemeColor(android.R.attr.colorPrimary)


🧱 ViewUtils
Advanced View utilities focused on performance.

view.setCornerRadius(
    radius = 32f,
    duration = 300,
    animate = true
)


🧠 Design Philosophy
Kotlin Extensions over inheritance

Minimal allocations

XML-free where possible

RecyclerView-safe

Hardware-accelerated UI operations

🚀 Installation
Copy the package into your project:


com.aboshekh.androidutils
No external dependencies required.
```
## ⭐ Author
Hassan Abdelaal (AboShekh)
Android Engineer — Kotlin • Custom UI • Performance Optimization

## 📄 MIT License

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

