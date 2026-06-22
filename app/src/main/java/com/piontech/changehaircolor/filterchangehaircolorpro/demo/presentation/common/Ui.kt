package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.common

import android.app.Activity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * Hides the system navigation bar (immersive). Re-call from onWindowFocusChanged so it stays
 * hidden after dialogs/keyboard; the bar reappears transiently on swipe.
 */
fun Activity.hideSystemNavBar() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controller = WindowInsetsControllerCompat(window, window.decorView)
    controller.hide(WindowInsetsCompat.Type.navigationBars())
    controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}
