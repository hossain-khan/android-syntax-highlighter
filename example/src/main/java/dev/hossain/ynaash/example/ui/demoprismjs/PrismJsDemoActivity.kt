package dev.hossain.yaash.example.ui.demoprismjs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.hossain.yaash.example.R
import dev.hossain.yaash.example.ui.common.SampleSourceCode
import dev.hossain.yaash.prismjs.SyntaxHighlighterFragment
import dev.hossain.yaash.prismjs.SyntaxHighlighterWebView

/**
 * Demo activity to showcase both fragment based and custom view based
 * syntax highlighting using Prism JS.
 *
 * @see SyntaxHighlighterFragment
 * @see SyntaxHighlighterWebView
 */
class PrismJsDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_prismjs)

        supportActionBar?.title = "PrismJS Demo"

        loadSourceCodeFragment()
        loadSourceCodeCustomView()
    }

    private fun loadSourceCodeFragment() {
        val fragment = SyntaxHighlighterFragment.newInstance(
            formattedSourceCode = SampleSourceCode.fragmentOnCreated,
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
            formattedSourceCode = SampleSourceCode.customViewBind,
            language = "kotlin",
            showLineNumbers = true
        )
    }
}