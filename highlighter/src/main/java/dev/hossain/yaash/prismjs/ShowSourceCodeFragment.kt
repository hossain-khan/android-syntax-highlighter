package dev.hossain.yaash.prismjs

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
import dev.hossain.yaash.webclient.AppWebViewClient
import dev.hossain.yaash.webclient.WebViewChromeClient

class ShowSourceCodeFragment : Fragment() {
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

        // https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(webView.settings, WebSettingsCompat.FORCE_DARK_ON)
        }

        webView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebViewChromeClient()
            webViewClient = AppWebViewClient()
            loadUrl("${ANDROID_ASSETS_PATH}_template.html")
        }
    }
}
