package dev.hossain.yaash.prismjs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import dev.hossain.yaash.webclient.AppWebViewClient
import dev.hossain.yaash.webclient.WebViewChromeClient

/**
 * Custom view that shows provided source code on custom view.
 */
class SyntaxHighlighterWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {
    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun bindSyntaxHighlighter(
        formattedSourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ) {
        settings.javaScriptEnabled = true
        webChromeClient = WebViewChromeClient()
        webViewClient = AppWebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH /* baseUrl */,
            prismJsHtmlContent(formattedSourceCode, language, showLineNumbers) /* html-data */,
            "text/html" /* mimeType */,
            "utf-8" /* encoding */,
            "" /* failUrl */
        )
    }
}