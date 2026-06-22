package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.app.Activity
import androidx.activity.ComponentActivity

class HextreeCallerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flag = intent.getStringExtra("flag")
        if (flag != null) {
            Toast.makeText(this, "Flag = $flag", Toast.LENGTH_LONG).show()
        }

        val resultIntent = Intent()
        resultIntent.putExtra("token", 1094795585)
        setResult(Activity.RESULT_OK, resultIntent)

        finish()
    }
}