package dev.hossain.ynaash.example.ui.demoprismjs

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.annotation.SuppressLint
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
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
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        supportActionBar?.title = "PrismJS Compose Demo"
        
        setContent {
            AppTheme {
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
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicColorScheme(context, darkTheme)
    } else if (darkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

/**
 * Creates a dynamic light/dark color scheme for Android 12+ (API 31+).
 * Marked [RequiresApi] so Lint understands it is only called behind an
 * SDK version check in [AppTheme]. The [SuppressLint] annotation silences
 * Lint's false-positive NewApi warning inside this guarded helper.
 */
@SuppressLint("NewApi")
@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun dynamicColorScheme(context: android.content.Context, darkTheme: Boolean) =
    if (darkTheme) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }

@Composable
fun PrismJsComposeDemoScreen() {
    val nestedScrollConnection = remember { object : NestedScrollConnection {} }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp)
            .nestedScroll(nestedScrollConnection)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Jetpack Compose Syntax Highlighting",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Jetpack Compose View Example",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            SyntaxHighlighter(
                sourceCode = SampleSourceCode.jetpackComposeView,
                language = "kotlin",
                modifier = Modifier.fillMaxSize(),
                showLineNumbers = false
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        
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
                modifier = Modifier.fillMaxSize(),
                showLineNumbers = true
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
                modifier = Modifier.fillMaxSize(),
                showLineNumbers = true
            )
        }
        
        Text(
            text = "Simple Example",
            style = MaterialTheme.typography.titleMedium
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            SyntaxHighlighter(
                sourceCode = "data class Student(val name: String, val age: Int)",
                language = "kotlin",
                modifier = Modifier.fillMaxSize(),
                showLineNumbers = false
            )
        }


        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun PrismJsComposeDemoScreenPreviewLight() {
    AppTheme {
        PrismJsComposeDemoScreen()
    }
}

@Preview(showBackground = true, name = "Dark Mode", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrismJsComposeDemoScreenPreviewDark() {
    AppTheme {
        PrismJsComposeDemoScreen()
    }
}