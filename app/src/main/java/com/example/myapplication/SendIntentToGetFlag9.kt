package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

@Composable
fun SendIntentToGetFlag9(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->

            val flag = result.data?.getStringExtra("flag")

            Toast.makeText(
                context,
                "Result Code = ${result.resultCode}, Flag = $flag",
                Toast.LENGTH_LONG
            ).show()
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Button(
            onClick = {

                val intent = Intent()

                intent.component =
                    ComponentName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.activities.Flag9Activity"
                    )

                launcher.launch(intent)

            }
        ) {

            Text("Flag 8")

        }

    }
}