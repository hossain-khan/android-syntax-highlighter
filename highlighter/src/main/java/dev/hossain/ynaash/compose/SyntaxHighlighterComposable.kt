package dev.hossain.ynaash.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import dev.hossain.ynaash.prismjs.SyntaxHighlighterWebView

/**
 * Jetpack Compose component that displays syntax highlighted source code.
 * 
 * This composable wraps the existing [SyntaxHighlighterWebView] to provide
 * a Compose-friendly interface for syntax highlighting.
 *
 * Example usage:
 * ```
 * SyntaxHighlighter(
 *     sourceCode = "data class Student(val name: String)",
 *     language = "kotlin",
 *     showLineNumbers = true
 * )
 * ```
 * 
 * @param sourceCode The source code to be syntax highlighted
 * @param language The programming language for syntax highlighting (e.g., "kotlin", "java", "javascript")
 * @param showLineNumbers Whether to display line numbers alongside the code
 * @param modifier Modifier to be applied to the syntax highlighter
 */
@Composable
fun SyntaxHighlighter(
    sourceCode: String,
    language: String,
    showLineNumbers: Boolean = false,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        AndroidView(
            modifier = Modifier,
            factory = { context ->
                SyntaxHighlighterWebView(context).apply {
                    bindSyntaxHighlighter(
                        formattedSourceCode = sourceCode,
                        language = language,
                        showLineNumbers = showLineNumbers
                    )
                }
            },
            update = { webView ->
                webView.bindSyntaxHighlighter(
                    formattedSourceCode = sourceCode,
                    language = language,
                    showLineNumbers = showLineNumbers
                )
            }
        )
    }
}