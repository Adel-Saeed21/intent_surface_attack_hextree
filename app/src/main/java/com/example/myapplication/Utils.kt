package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

object Utils {

    fun dumpIntent(context: Context, intent: Intent?): String {
        return dumpIntent(context, intent, 0)
    }

    private fun dumpIntent(
        context: Context,
        intent: Intent?,
        indentLevel: Int
    ): String {

        if (intent == null) {
            return "Intent is null"
        }

        val sb = StringBuilder()
        val indent = "    ".repeat(indentLevel)

        sb.append(indent).append("[Action]    ")
            .append(intent.action)
            .append("\n")

        intent.categories?.forEach { category ->
            sb.append(indent)
                .append("[Category]  ")
                .append(category)
                .append("\n")
        }

        sb.append(indent)
            .append("[Data]      ")
            .append(intent.dataString)
            .append("\n")

        sb.append(indent)
            .append("[Component] ")
            .append(intent.component)
            .append("\n")

        sb.append(indent)
            .append("[Flags]     ")
            .append(getFlagsString(intent.flags))
            .append("\n")

        val extras = intent.extras

        extras?.keySet()?.forEach { key ->
            val value = extras.get(key)

            when (value) {
                is Intent -> {
                    sb.append(indent)
                        .append("[Extra:'")
                        .append(key)
                        .append("'] -> Intent\n")

                    sb.append(
                        dumpIntent(
                            context,
                            value,
                            indentLevel + 1
                        )
                    )
                }

                is Bundle -> {
                    sb.append(indent)
                        .append("[Extra:'")
                        .append(key)
                        .append("'] -> Bundle\n")

                    sb.append(
                        dumpBundle(
                            value,
                            indentLevel + 1
                        )
                    )
                }

                else -> {
                    sb.append(indent)
                        .append("[Extra:'")
                        .append(key)
                        .append("']: ")
                        .append(value)
                        .append("\n")
                }
            }
        }

        return sb.toString()
    }

    fun dumpBundle(bundle: Bundle?): String {
        return dumpBundle(bundle, 0)
    }

    private fun dumpBundle(
        bundle: Bundle?,
        indentLevel: Int
    ): String {

        if (bundle == null) {
            return "Bundle is null"
        }

        val sb = StringBuilder()
        val indent = "    ".repeat(indentLevel)

        bundle.keySet().forEach { key ->
            val value = bundle.get(key)

            if (value is Bundle) {
                sb.append(
                    String.format(
                        "%s['%s']: Bundle[\n%s%s]\n",
                        indent,
                        key,
                        dumpBundle(value, indentLevel + 1),
                        indent
                    )
                )
            } else {
                sb.append(
                    String.format(
                        "%s['%s']: %s\n",
                        indent,
                        key,
                        value?.toString() ?: "null"
                    )
                )
            }
        }

        return sb.toString()
    }

    private fun getFlagsString(flags: Int): String {

        val flagBuilder = StringBuilder()

        if (flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
            flagBuilder.append("GRANT_READ_URI_PERMISSION | ")

        if (flags and Intent.FLAG_GRANT_WRITE_URI_PERMISSION != 0)
            flagBuilder.append("GRANT_WRITE_URI_PERMISSION | ")

        if (flags and Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION != 0)
            flagBuilder.append("GRANT_PERSISTABLE_URI_PERMISSION | ")

        if (flags and Intent.FLAG_GRANT_PREFIX_URI_PERMISSION != 0)
            flagBuilder.append("GRANT_PREFIX_URI_PERMISSION | ")

        if (flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0)
            flagBuilder.append("ACTIVITY_NEW_TASK | ")

        if (flags and Intent.FLAG_ACTIVITY_SINGLE_TOP != 0)
            flagBuilder.append("ACTIVITY_SINGLE_TOP | ")

        if (flags and Intent.FLAG_ACTIVITY_NO_HISTORY != 0)
            flagBuilder.append("ACTIVITY_NO_HISTORY | ")

        if (flags and Intent.FLAG_ACTIVITY_CLEAR_TOP != 0)
            flagBuilder.append("ACTIVITY_CLEAR_TOP | ")

        if (flags and Intent.FLAG_ACTIVITY_FORWARD_RESULT != 0)
            flagBuilder.append("ACTIVITY_FORWARD_RESULT | ")

        if (flags and Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP != 0)
            flagBuilder.append("ACTIVITY_PREVIOUS_IS_TOP | ")

        if (flags and Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS != 0)
            flagBuilder.append("ACTIVITY_EXCLUDE_FROM_RECENTS | ")

        if (flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0)
            flagBuilder.append("ACTIVITY_BROUGHT_TO_FRONT | ")

        if (flags and Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED != 0)
            flagBuilder.append("ACTIVITY_RESET_TASK_IF_NEEDED | ")

        if (flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY != 0)
            flagBuilder.append("ACTIVITY_LAUNCHED_FROM_HISTORY | ")

        if (flags and Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET != 0)
            flagBuilder.append("ACTIVITY_CLEAR_WHEN_TASK_RESET | ")

        if (flags and Intent.FLAG_ACTIVITY_NEW_DOCUMENT != 0)
            flagBuilder.append("ACTIVITY_NEW_DOCUMENT | ")

        if (flags and Intent.FLAG_ACTIVITY_NO_USER_ACTION != 0)
            flagBuilder.append("ACTIVITY_NO_USER_ACTION | ")

        if (flags and Intent.FLAG_ACTIVITY_REORDER_TO_FRONT != 0)
            flagBuilder.append("ACTIVITY_REORDER_TO_FRONT | ")

        if (flags and Intent.FLAG_ACTIVITY_NO_ANIMATION != 0)
            flagBuilder.append("ACTIVITY_NO_ANIMATION | ")

        if (flags and Intent.FLAG_ACTIVITY_CLEAR_TASK != 0)
            flagBuilder.append("ACTIVITY_CLEAR_TASK | ")

        if (flags and Intent.FLAG_ACTIVITY_TASK_ON_HOME != 0)
            flagBuilder.append("ACTIVITY_TASK_ON_HOME | ")

        if (flags and Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS != 0)
            flagBuilder.append("ACTIVITY_RETAIN_IN_RECENTS | ")

        if (flags and Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT != 0)
            flagBuilder.append("ACTIVITY_LAUNCH_ADJACENT | ")

        if (flags and Intent.FLAG_ACTIVITY_REQUIRE_DEFAULT != 0)
            flagBuilder.append("ACTIVITY_REQUIRE_DEFAULT | ")

        if (flags and Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER != 0)
            flagBuilder.append("ACTIVITY_REQUIRE_NON_BROWSER | ")

        if (flags and Intent.FLAG_ACTIVITY_MATCH_EXTERNAL != 0)
            flagBuilder.append("ACTIVITY_MATCH_EXTERNAL | ")

        if (flags and Intent.FLAG_ACTIVITY_MULTIPLE_TASK != 0)
            flagBuilder.append("ACTIVITY_MULTIPLE_TASK | ")

        if (flags and Intent.FLAG_RECEIVER_REGISTERED_ONLY != 0)
            flagBuilder.append("RECEIVER_REGISTERED_ONLY | ")

        if (flags and Intent.FLAG_RECEIVER_REPLACE_PENDING != 0)
            flagBuilder.append("RECEIVER_REPLACE_PENDING | ")

        if (flags and Intent.FLAG_RECEIVER_FOREGROUND != 0)
            flagBuilder.append("RECEIVER_FOREGROUND | ")

        if (flags and Intent.FLAG_RECEIVER_NO_ABORT != 0)
            flagBuilder.append("RECEIVER_NO_ABORT | ")

        if (flags and Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS != 0)
            flagBuilder.append("RECEIVER_VISIBLE_TO_INSTANT_APPS | ")

        if (flagBuilder.isNotEmpty()) {
            flagBuilder.setLength(flagBuilder.length - 3)
        }

        return flagBuilder.toString()
    }

    fun showDialog(context: Context, intent: Intent?) {

        if (intent == null) return

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 50, 20, 50)
            setBackgroundColor(0xffefeff5.toInt())
        }

        val title = TextView(context).apply {
            text = "Intent Details:"
            textSize = 16f
            setTextColor(0xff000000.toInt())
            setTypeface(Typeface.DEFAULT, Typeface.BOLD)
            setPadding(0, 0, 0, 40)
            gravity = Gravity.CENTER
            setBackgroundColor(0xffefeff5.toInt())
        }

        layout.addView(title)

        val message = TextView(context).apply {
            text = dumpIntent(context, intent)
            typeface = Typeface.MONOSPACE
            textSize = 12f
            setTextColor(0xff000000.toInt())
            setPadding(0, 0, 0, 30)
            gravity = Gravity.START
            setBackgroundColor(0xffefeff5.toInt())
        }

        layout.addView(message)

        val button = Button(context).apply {
            text = "OK"
            setTextColor(0xff000000.toInt())
            setOnClickListener {
                dialog.dismiss()
            }
        }

        layout.addView(button)

        dialog.setContentView(layout)

        dialog.window?.let { window ->
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            window.setBackgroundDrawableResource(
                android.R.color.transparent
            )

            val params = window.attributes
            params.gravity = Gravity.BOTTOM
            params.flags =
                params.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()

            window.attributes = params
        }

        dialog.show()

        layout.translationY = 2000f
        layout.alpha = 0f

        val translateAnimator =
            ObjectAnimator.ofFloat(layout, "translationY", 0f)

        val alphaAnimator =
            ObjectAnimator.ofFloat(layout, "alpha", 1f)

        AnimatorSet().apply {
            playTogether(translateAnimator, alphaAnimator)
            duration = 300
            startDelay = 100
            start()
        }
    }
}