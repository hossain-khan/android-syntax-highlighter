package dev.hossain.ynaash.prismjs

/**
 * This is an example how template can be defined with variables.
 *
 * You may need to add more variables to have more customizations through additional plugins.
 * For example:
 * - Highlight line numbers
 * - Show invisible characters
 * - and so on
 * @see <a href="https://prismjs.com/download.html">Prism JS Download</a> options for more information.
 */
fun prismJsHtmlContent(
    formattedSourceCode: String,
    language: String, // Language available via plugin https://prismjs.com/index.html#supported-languages
    showLineNumbers: Boolean = true
): String {
    return """<!DOCTYPE html>
<html>
<head>
    <!-- https://developer.chrome.com/multidevice/webview/pixelperfect -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="www/main.css" rel="stylesheet"/>

    <!-- https://prismjs.com/ -->
    <link href="www/prism.css" rel="stylesheet"/>
    <script src="www/prism.js"></script>
    <!-- HTML/XML encode entities: https://mothereff.in/html-entities -->
</head>
<body>
<pre class="${if (showLineNumbers) "line-numbers" else ""}">
<code class="language-${language}">${formattedSourceCode}</code>
</pre>
</body>
</html>
"""
}