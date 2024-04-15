package com.leads.capitabull.android.session

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.leads.capitabull.android.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
object MyConstants {
    const val IS_SESSION_TIMEOUT = "is_session_timeout"
}
class SessionManager(
    private var period: Long,
    private var context: Context
) {
    private val SLEEP_TIME: Long = 1 * 1000000
    private var lastUsed: Long = 0
    private var stop = false

    private val TAG = SessionManager::class.java.name

    fun run() {
        CoroutineScope(Dispatchers.IO).launch {
            var idle: Long = 0
            touch()
            while (!stop) {
                idle = System.currentTimeMillis() - lastUsed
                Log.d(TAG, "Application is idle for $idle ms")
                delay(SLEEP_TIME) // check every 5 seconds
                if (idle > period) {
                    idle = 0
                    showTimeoutDialogInMainThread()
                    stopp()
                }
            }
            Log.d(TAG, "Finishing Waiter thread")
        }
    }

    private fun showTimeoutDialogInMainThread() {
        CoroutineScope(Dispatchers.Main).launch {
            showTimeoutDialog()
        }
    }

    private fun showTimeoutDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Session Timeout")
        builder.setMessage("Your session has timed out. Please log in again.")


        var dialogClicked = false
        builder.setPositiveButton("OK") { dialog, _ ->
            dialogClicked = true
            moveToLogin()
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (!dialogClicked) {
                dialog.dismiss()
                moveToLogin()
            }
        }, 6000) // Delay for 6 seconds
    }

    private fun moveToLogin() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(MyConstants.IS_SESSION_TIMEOUT, true)
        }
        context.startActivity(intent)
    }

    fun touch() {
        lastUsed = System.currentTimeMillis()
        Log.d("used time",lastUsed.toString())
    }

    fun forceInterrupt() {
        // You can use coroutine cancellation here
    }

    // Soft stopping of the thread
    fun stopp() {
        stop = true
    }

    fun setPeriod(period: Long) {
        this.period = period
    }
}
