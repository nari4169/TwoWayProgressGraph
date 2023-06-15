package com.example.twowayprogressgraph.util

import android.util.Log
import com.example.twowayprogressgraph.BuildConfig

/**
 * Custom TAG ->
 * [FileName](StackTrace) >MethodName
 * or Input Value
 * or Default TAG
 */

private const val LOG_TAG = "App"
object Logger {
    fun e(`object`: Any) {
        if (BuildConfig.DEBUG) {
            Log.e(tag(), `object`.toString())
        }
    }

    fun e(tag: String? = null, `object`: Any) {
        if (BuildConfig.DEBUG) {
            Log.e(tag ?: tag(), `object`.toString())
        }
    }

    fun ee() {
        if (BuildConfig.DEBUG) {
            Log.e(tag(), " ")
        }
    }

    private fun tag(): String {
        val arrStackTraceElement = Thread.currentThread().stackTrace
        return if (arrStackTraceElement.size >= 4) {
            val ste = arrStackTraceElement[4]
            val sb = StringBuilder()
            sb.append("[")
            sb.append(ste.fileName)
            sb.append("]")
            sb.append("(")
            sb.append(ste.fileName)
            sb.append(":")
            sb.append(ste.lineNumber)
            sb.append(")")
            sb.append(" >")
            sb.append(ste.methodName)
            sb.toString()
        } else {
            LOG_TAG
        }
    }

}