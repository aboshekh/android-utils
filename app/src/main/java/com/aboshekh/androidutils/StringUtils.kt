@file:JvmName("StringUtils")

package com.aboshekh.androidutils

/**
 * Converts uppercase letters in a string to lowercase
 * and inserts a separator before each uppercase letter.
 *
 * Commonly used to transform camelCase or PascalCase strings
 * into snake_case or spaced formats.
 *
 * @param separator The string to insert before each uppercase letter.
 *                  Defaults to "_".
 *
 * @return Transformed lowercase string with separators.
 *
 * Examples:
 * "myClassName".lowercaseWithSeparator() -> "my_class_name"
 * "myClassName".lowercaseWithSeparator(" ") -> "my class name"
 */
fun String.lowercaseWithSeparator(separator: String = "_"): String {
    return replace(Regex("([A-Z])")) {
        separator + it.value.lowercase()
    }
}

/**
 * Converts a PascalCase string to snake_case.
 *
 * Removes the leading separator caused by the first uppercase letter.
 *
 * Example:
 * "MyClassName".pascalToSnakeCase() -> "my_class_name"
 */
fun String.pascalToSnakeCase(): String =
    lowercaseWithSeparator("_").replaceFirst("_", "")

/**
 * Converts a camelCase string to snake_case.
 *
 * Example:
 * "myClassName".camelToSnakeCase() -> "my_class_name"
 */
fun String.camelToSnakeCase(): String =
    lowercaseWithSeparator("_")
