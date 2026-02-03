package dev.hossain.ynaash.webclient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * A custom [WebViewClient] for handling web events within the syntax highlighter.
 *
 * NOTE: Because of gradle dependency, it Will use `WebViewCompat` under the hood.
 * - https://developer.android.com/reference/androidx/webkit/WebViewCompat
 * - https://stackoverflow.com/questions/52765057/sample-usage-of-webviewcompat
 * - https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
 */
class AppWebViewClient : WebViewClient() {
    companion object {
        private const val LOG_TAG = "YaashWebViewClient"
    }

    /**
     * Overrides URL loading to prevent navigation within the WebView.
     * All external URLs are intended to be opened via an external browser.
     */
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return true // Open any external URL via external browser
    }

    /**
     * Called when the page starts loading.
     */
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.d(LOG_TAG, "onPageStarted")
        super.onPageStarted(view, url, favicon)
    }

    /**
     * Called when the page has finished loading.
     */
    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d(LOG_TAG, "onPageFinished")
        super.onPageFinished(view, url)
    }

    /**
     * Called when there is an error during page loading.
     */
    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        Log.d(LOG_TAG, "onReceivedError")

        view?.loadUrl("about:blank")
    }
}
