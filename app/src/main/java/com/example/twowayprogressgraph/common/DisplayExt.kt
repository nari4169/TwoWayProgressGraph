package com.example.twowayprogressgraph.common

import android.content.res.Resources

object DisplayExt {
    fun Int.toPx() : Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun Int.toDp() : Int {
        return (this / Resources.getSystem().displayMetrics.density).toInt()
    }
}