package dev.hossain.ynaash.example.ui.demoprismjs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.hossain.ynaash.example.R
import dev.hossain.ynaash.example.ui.common.SampleSourceCode
import dev.hossain.ynaash.prismjs.SyntaxHighlighterFragment
import dev.hossain.ynaash.prismjs.SyntaxHighlighterWebView

/**
 * Demo activity to showcase both fragment based and custom view based
 * syntax highlighting using Prism JS.
 *
 * @see SyntaxHighlighterFragment
 * @see SyntaxHighlighterWebView
 */
class PrismJsDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_prismjs)

        supportActionBar?.title = "PrismJS Demo"

        // Apply window insets to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.demo_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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