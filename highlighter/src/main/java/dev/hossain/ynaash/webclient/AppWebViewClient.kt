package dev.hossain.ynaash.webclient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * NOTE: Because of gradle dependency, it Will use `WebViewCompat` under the hood.
 * - https://developer.android.com/reference/androidx/webkit/WebViewCompat
 * - https://stackoverflow.com/questions/52765057/sample-usage-of-webviewcompat
 * - https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
 */
class AppWebViewClient : WebViewClient() {
    companion object {
        private const val LOG_TAG = "YaashWebViewClient"
    }

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return true // Open any external URL via external browser
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.d(LOG_TAG, "onPageStarted")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d(LOG_TAG, "onPageFinished")
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        Log.d(LOG_TAG, "onReceivedError")

        view?.loadUrl("about:blank")
    }
}
