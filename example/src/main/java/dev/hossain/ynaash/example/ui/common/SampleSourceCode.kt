package dev.hossain.ynaash.example.ui.common

object SampleSourceCode {

    //
    // Sample codes for syntax-highlight preview
    //

    val fragmentOnCreated = """
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

    val customViewBind = """
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

    val jetpackComposeView = """
    |SyntaxHighlighter(
    |    sourceCode = "data class Student(val name: String, val age: Int)",
    |    language = "kotlin",
    |    showLineNumbers = false,
    |    modifier = Modifier.fillMaxSize()
    |)
    """.trimMargin()
}