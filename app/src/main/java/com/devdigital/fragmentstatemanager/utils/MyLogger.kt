package com.devdigital.fragmentstatemanager.utils

import android.util.Log

/**
 * @author Hitesh
 * @version 1.0
 * @since 22-08-2019
 */
class MyLogger {
    var sb0 = StringBuilder()
    // reusable string object
    fun logWithLink(TAG: String, param: Any?) {
        val stack = Thread.currentThread().stackTrace[3]
        sb0.setLength(0)
        val c = stack.fileName.substring(0, stack.fileName.length - 5)
        // removes the ".java"
        sb0.append(c).append(":")
        sb0.append(stack.methodName).append('(')
        if (param != null) {
            sb0.append(param)
        }
        sb0.append(") ")
        sb0.append(" (").append(stack.fileName).append(':').append(stack.lineNumber).append(')')
        Log.d(TAG, sb0.toString())
    }

}