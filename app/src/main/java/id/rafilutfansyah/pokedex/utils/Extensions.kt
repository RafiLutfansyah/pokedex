package id.rafilutfansyah.pokedex.utils

import android.content.Context
import android.util.TypedValue

fun Int.toDp(context: Context): Int {
    val resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), resources.displayMetrics
    ).toInt()
}

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")