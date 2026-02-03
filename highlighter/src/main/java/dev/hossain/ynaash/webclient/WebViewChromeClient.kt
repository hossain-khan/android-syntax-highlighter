package dev.hossain.ynaash.webclient

import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient

/**
 * A custom [WebChromeClient] for handling Chrome-related events within the syntax highlighter.
 */
class WebViewChromeClient : WebChromeClient() {
    /**
     * Handles console messages from the WebView.
     * Currently configured to disable console messages in logcat.
     */
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return true // disable console message on logcat
    }
}
