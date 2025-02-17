package com.leads.capita.android.shell
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

@Composable
fun SetStatusBarColor(color: Color, color1: Color) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val window = (context as? ComponentActivity)?.window

//    AndroidView(factory = { viewContext ->
//        View(viewContext).apply {
//            setBackgroundColor(color.toArgb())
//        }
//    }, update = { view ->
//        view.setBackgroundColor(color.toArgb())
//    })

    lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            window?.statusBarColor = color.toArgb()
            window?.navigationBarColor = color1.toArgb()
        }
    })
}


