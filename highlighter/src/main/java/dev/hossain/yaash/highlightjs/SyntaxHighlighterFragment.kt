package dev.hossain.yaash.highlightjs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import dev.hossain.yaash.R
import dev.hossain.yaash.highlightjs.SyntaxHighlighterFragment.Companion.newInstance
import dev.hossain.yaash.webclient.AppWebViewClient
import dev.hossain.yaash.webclient.WebViewChromeClient

/**
 * Fragment that shows syntax highlighted source code.
 * Use [newInstance] to provide source code and other configuration parameters.
 *
 * Here is an example of how to add the fragment from an activity:
 * ```
 *   val fragment = SyntaxHighlighterFragment.newInstance(
 *       formattedSourceCode = "data class Student(val name: String)",
 *       language = "kotlin",
 *       showLineNumbers = true
 *   )
 *
 *   val fragmentManager = supportFragmentManager
 *   val fragmentTransaction = fragmentManager.beginTransaction()
 *   fragmentTransaction.add(R.id.fragment_container, fragment)
 *   fragmentTransaction.commit()
 * ```
 */
class SyntaxHighlighterFragment : Fragment() {
    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"

        private const val ARG_KEY_SOURCE_CODE_CONTENT = "ARG_SOURCE_CODE"
        private const val ARG_KEY_CODE_LANGUAGE = "ARG_SOURCE_CODE_LANGUAGE_NAME"

        /**
         * Creates new instance of the fragment with required values to render the syntax highlighted code.
         */
        fun newInstance(
            formattedSourceCode: String,
            language: String
        ) = SyntaxHighlighterFragment().apply {
            arguments = Bundle(2).apply {
                putString(ARG_KEY_SOURCE_CODE_CONTENT, formattedSourceCode)
                putString(ARG_KEY_CODE_LANGUAGE, language)
            }
        }
    }

    private val sourceCode: String by lazy {
        arguments?.getString(ARG_KEY_SOURCE_CODE_CONTENT)
            ?: throw IllegalArgumentException("Use `newInstance()` to provide required data")
    }
    private val language: String by lazy {
        arguments?.getString(ARG_KEY_CODE_LANGUAGE)
            ?: throw IllegalArgumentException("Use `newInstance()` to provide required data")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_highlighter, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView: WebView = view.findViewById(R.id.web_view)

        // https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(webView.settings, WebSettingsCompat.FORCE_DARK_ON)
        }

        webView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebViewChromeClient()
            webViewClient = AppWebViewClient()

            loadDataWithBaseURL(
                ANDROID_ASSETS_PATH /* baseUrl */,
                highlightJsHtmlContent(sourceCode, language) /* html-data */,
                "text/html" /* mimeType */,
                "utf-8" /* encoding */,
                "" /* failUrl */
            )
        }
    }
}
