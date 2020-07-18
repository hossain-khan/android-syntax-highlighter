package dev.hossain.yaash.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.hossain.yaash.prismjs.SyntaxHighlighterFragment
import dev.hossain.yaash.prismjs.SyntaxHighlighterWebView

/**
 * Main activity to showcase both fragment based and custom view based syntax highlighting.
 *
 * @see SyntaxHighlighterFragment
 * @see SyntaxHighlighterWebView
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadSourceCodeFragment()
        loadSourceCodeCustomView()
    }

    private fun loadSourceCodeFragment() {
        val fragment = SyntaxHighlighterFragment.newInstance(
            formattedSourceCode = fragmentSourceCode,
            language = "kotlin",
            showLineNumbers = true
        )

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun loadSourceCodeCustomView() {
        val syntaxHighlighter: SyntaxHighlighterWebView = findViewById(R.id.syntax_highlighter_webview)

        syntaxHighlighter.bindSyntaxHighlighter(
            formattedSourceCode = customViewSourceCode,
            language = "kotlin",
            showLineNumbers = true
        )
    }

    //
    // Sample codes for syntax-highlight preview
    //

    private val fragmentSourceCode = """
    |@SuppressLint("SetJavaScriptEnabled")
    |override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    |    super.onViewCreated(view, savedInstanceState)
    |    val webView: WebView = view.findViewById(R.id.web_view)
    |
    |    // https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
    |    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
    |        WebSettingsCompat.setForceDark(webView.settings, WebSettingsCompat.FORCE_DARK_ON)
    |    }
    |
    |    webView.apply {
    |        settings.javaScriptEnabled = true
    |        webChromeClient = WebViewChromeClient()
    |        webViewClient = AppWebViewClient()
    |
    |        loadDataWithBaseURL(
    |            ANDROID_ASSETS_PATH /* baseUrl */,
    |            prismJsHtmlContent(sourceCode, language, showLineNumbers) /* html-data */,
    |            "text/html" /* mimeType */,
    |            "utf-8" /* encoding */,
    |            "" /* failUrl */
    |        )
    |    }
    |}
    """.trimMargin()

    private val customViewSourceCode = """
    |@SuppressLint("SetJavaScriptEnabled")
    |fun bindSyntaxHighlighter(
    |    formattedSourceCode: String,
    |    language: String,
    |    showLineNumbers: Boolean = false
    |) {
    |    settings.javaScriptEnabled = true
    |    webChromeClient = WebViewChromeClient()
    |    webViewClient = AppWebViewClient()
    |
    |    loadDataWithBaseURL(
    |        ANDROID_ASSETS_PATH /* baseUrl */,
    |        prismJsHtmlContent(formattedSourceCode, language, showLineNumbers) /* html-data */,
    |        "text/html" /* mimeType */,
    |        "utf-8" /* encoding */,
    |        "" /* failUrl */
    |    )
    |}
    """.trimMargin()
}