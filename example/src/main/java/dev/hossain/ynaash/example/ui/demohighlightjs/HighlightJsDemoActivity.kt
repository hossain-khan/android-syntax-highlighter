package dev.hossain.ynaash.example.ui.demohighlightjs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.hossain.ynaash.example.R
import dev.hossain.ynaash.example.ui.common.SampleSourceCode
import dev.hossain.ynaash.highlightjs.SyntaxHighlighterFragment
import dev.hossain.ynaash.highlightjs.SyntaxHighlighterWebView

/**
 * Demo activity to showcase both fragment based and custom view based
 * syntax highlighting using highlight.js
 *
 * @see SyntaxHighlighterFragment
 * @see SyntaxHighlighterWebView
 */
class HighlightJsDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_highlightjs)

        supportActionBar?.title = "HighlightJS Demo"

        loadSourceCodeFragment()
        loadSourceCodeCustomView()
    }

    private fun loadSourceCodeFragment() {
        val fragment = SyntaxHighlighterFragment.newInstance(
            formattedSourceCode = SampleSourceCode.fragmentOnCreated,
            language = "kotlin"
        )

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun loadSourceCodeCustomView() {
        val syntaxHighlighter: SyntaxHighlighterWebView = findViewById(R.id.syntax_highlighter_webview)

        syntaxHighlighter.bindSyntaxHighlighter(
            formattedSourceCode = SampleSourceCode.customViewBind,
            language = "kotlin"
        )
    }
}