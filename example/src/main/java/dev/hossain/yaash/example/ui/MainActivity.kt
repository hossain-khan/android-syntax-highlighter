package dev.hossain.yaash.example.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dev.hossain.yaash.example.R
import dev.hossain.yaash.example.ui.demohighlightjs.HighlightJsDemoActivity
import dev.hossain.yaash.example.ui.demoprismjs.PrismJsDemoActivity

/**
 * Main activity to showcase both fragment based and custom view based syntax highlighting.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.highlightjs_demo_button).setOnClickListener {
            startActivity(Intent(this, HighlightJsDemoActivity::class.java))
        }

        findViewById<Button>(R.id.prismjs_demo_button).setOnClickListener {
            startActivity(Intent(this, PrismJsDemoActivity::class.java))
        }
    }
}