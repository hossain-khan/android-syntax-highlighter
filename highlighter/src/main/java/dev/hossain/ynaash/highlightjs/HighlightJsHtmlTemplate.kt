package dev.hossain.ynaash.highlightjs

/**
 * This is an example how template can be defined with variables.
 * @see <a href="https://highlightjs.org/usage/">Highlight JS Usage</a> for more information.
 */
fun highlightJsHtmlContent(
    formattedSourceCode: String,
    language: String
): String {
    return """<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="www/main.css" rel="stylesheet"/>

    <link rel="stylesheet" href="www/highlight.theme.default.css">
    <!-- Try themes at https://highlightjs.org/static/demo/ -->
    <link rel="stylesheet" href="www/highlight.theme.night-owl.css">

    <script src="www/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<pre>
<code class="language-${language}">${formattedSourceCode}</code>
</pre>
</body>
</html>
"""
}