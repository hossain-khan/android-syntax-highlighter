package dev.hossain.yaash.webclient

import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient

class WebViewChromeClient : WebChromeClient() {
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return true // disable console message on logcat
    }
}
