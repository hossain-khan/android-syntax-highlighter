package dev.hossain.ynaash.prismjs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import dev.hossain.ynaash.R
import dev.hossain.ynaash.webclient.AppWebViewClient
import dev.hossain.ynaash.webclient.WebViewChromeClient

/**
 * Fragment that loads hard coded static HTML file with syntax highlighting.
 *
 * @see SyntaxHighlighterFragment
 */
class ShowStaticSourceCodeFragment : Fragment() {
    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    private lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_highlighter, container, false)
        webView = rootView.findViewById(R.id.web_view)
        return rootView
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
            WebSettingsCompat.setAlgorithmicDarkeningAllowed(webView.settings, true)
        }

        webView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebViewChromeClient()
            webViewClient = AppWebViewClient()
            loadUrl("${ANDROID_ASSETS_PATH}prismjs_template.html")
        }
    }
}
