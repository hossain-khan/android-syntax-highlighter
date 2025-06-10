package dev.hossain.ynaash.example.ui.demoprismjs

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hossain.ynaash.compose.SyntaxHighlighter
import dev.hossain.ynaash.example.ui.common.SampleSourceCode

/**
 * Demo activity to showcase Jetpack Compose based syntax highlighting using PrismJS.
 * 
 * This activity demonstrates how to use the [SyntaxHighlighter] composable
 * to display syntax highlighted code in a Compose UI.
 */
class PrismJsComposeDemoActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        supportActionBar?.title = "PrismJS Compose Demo"
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrismJsComposeDemoScreen()
                }
            }
        }
    }
}

@Composable
fun PrismJsComposeDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Jetpack Compose Syntax Highlighting",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Text(
            text = "Fragment onViewCreated Example",
            style = MaterialTheme.typography.titleMedium
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            SyntaxHighlighter(
                sourceCode = SampleSourceCode.fragmentOnCreated,
                language = "kotlin",
                showLineNumbers = true,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        Text(
            text = "Custom View Bind Example",
            style = MaterialTheme.typography.titleMedium
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            SyntaxHighlighter(
                sourceCode = SampleSourceCode.customViewBind,
                language = "kotlin",
                showLineNumbers = true,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        Text(
            text = "Simple Example",
            style = MaterialTheme.typography.titleMedium
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            SyntaxHighlighter(
                sourceCode = "data class Student(val name: String, val age: Int)",
                language = "kotlin",
                showLineNumbers = false,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrismJsComposeDemoScreenPreview() {
    MaterialTheme {
        PrismJsComposeDemoScreen()
    }
}