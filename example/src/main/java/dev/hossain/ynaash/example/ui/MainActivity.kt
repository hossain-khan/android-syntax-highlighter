package dev.hossain.ynaash.example.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dev.hossain.ynaash.example.R
import dev.hossain.ynaash.example.ui.demohighlightjs.HighlightJsDemoActivity
import dev.hossain.ynaash.example.ui.demoprismjs.PrismJsDemoActivity
import dev.hossain.ynaash.example.ui.demoprismjs.PrismJsComposeDemoActivity

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

        findViewById<Button>(R.id.prismjs_compose_demo_button).setOnClickListener {
            startActivity(Intent(this, PrismJsComposeDemoActivity::class.java))
        }
    }
}