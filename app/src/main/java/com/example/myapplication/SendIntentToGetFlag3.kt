package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
fun SendIntentToGetFlag3(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {

                val intent = Intent().apply {

                    component = ComponentName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.activities.Flag3Activity"
                    )
                    action="io.hextree.action.GIVE_FLAG"
                    data = "https://app.hextree.io/map/android".toUri()
                }

                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors()
        ) {

            Text("send Intent")

        }
    }
}