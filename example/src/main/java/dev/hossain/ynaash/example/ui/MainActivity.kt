package dev.hossain.ynaash.example.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.hossain.ynaash.example.R
import dev.hossain.ynaash.example.ui.demohighlightjs.HighlightJsDemoActivity
import dev.hossain.ynaash.example.ui.demoprismjs.PrismJsDemoActivity
import dev.hossain.ynaash.example.ui.demoprismjs.PrismJsComposeDemoActivity

/**
 * Main activity to showcase both fragment based and custom view based syntax highlighting.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Apply window insets to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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