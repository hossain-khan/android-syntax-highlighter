package dev.hossain.ynaash.highlightjs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import dev.hossain.ynaash.webclient.AppWebViewClient
import dev.hossain.ynaash.webclient.WebViewChromeClient

/**
 * Custom view that shows provided source code on custom view.
 *
 * Here is an example how to load syntax highlighting using this custom view:
 *
 * ```
 * <!-- Add the view in your Activity or Fragment layout -->
 * <dev.hossain.yaash.highlightjs.SyntaxHighlighterWebView
 *   android:id="@+id/syntax_highlighter_webview"
 *   android:layout_width="match_parent"
 *   android:layout_height="match_parent" />
 * ```
 *
 * Once the Activity or Fragment is ready, binding the `SyntaxHighlighterWebView` with source coded and config.
 * ```
 *  val highlighter: SyntaxHighlighterWebView = findViewById(R.id.syntax_highlighter_webview)
 *
 *  highlighter.bindSyntaxHighlighter(
 *      formattedSourceCode = "data class Student(val name: String)",
 *      language = "kotlin"
 *  )
 * ```
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
        language: String
    ) {
        settings.javaScriptEnabled = true
        webChromeClient = WebViewChromeClient()
        webViewClient = AppWebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH /* baseUrl */,
            highlightJsHtmlContent(formattedSourceCode, language) /* html-data */,
            "text/html" /* mimeType */,
            "utf-8" /* encoding */,
            "" /* failUrl */
        )
    }
}