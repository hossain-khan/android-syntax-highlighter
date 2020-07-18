# android-syntax-highlighter
yet another android syntax highlighter (YAASH)

### Objective
Explore well established web based syntax highlighter like [PrismJS](https://prismjs.com/) and [highlight.js](https://highlightjs.org/), and showcase how anybody can quickly incorporate these into their project by following some examples provided here.

The objective is not exactly to provide a _library_ that you can use with Gradle.


## Existing Syntax Highlighting Libraries

If you need a library, you may look into following existing projects 

1. [CodeView-Android](https://github.com/kbiakov/CodeView-Android) - Display code with syntax highlighting ✨ in native way.  
703 :star:, Last updated: Jan 24, 2019
1. [highlightjs-android](https://github.com/PDDStudio/highlightjs-android) - A view for source code syntax highlighting on Android.  
 283 :star:, Last updated: Sep 8, 2018
1. [Syntax-View-Android](https://github.com/Badranh/Syntax-View-Android) - Beautiful Android Syntax View with line counter it will automatically highlight the code.  
42 :star:, Last updated: Mar 24, 2020

> _NOTE: The 'Last updated' and :star: data was taken as of July 16th, 2020_

------------------------
 
## Under the hood
Here is how you would have syntax highlighting using any modern JavaScript library.

### 1. Choose JS Library
There are several popular syntax highlighters. Here I have used Prism JS because it's light weight and one of the popular one.   

Follow their documentation to download the library with the plugins you need. For example, showing line number is a plugin, that is how they can keep the library so light weight like `2KB` core. 

### 2. Use HTML+CSS+JS Asset
Move downloaded `prism.js` and `prism.css` to `assets` resource directory. For example, here I have moved them to "[assets/www](https://github.com/amardeshbd/android-syntax-highlighter/tree/develop/highlighter/src/main/assets/www)" folder.

Write plain HTML that loads these assets and your source code.

For example:
```html
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="www/prism.css" rel="stylesheet"/>
        <script src="www/prism.js"></script>
    </head>
    <body>
        <h1>Demo Syntax Highlight</h1>
        <p>Description about the code.</p>
        <pre class="line-numbers">
        <code class="language-kotlin">class MainActivity : AppCompatActivity() { /* ... */ }
        </code>
        </pre>
    </body>
</html>
```

> NOTE: For most cases, hard coding sample code for each sample-code is not ideal. 
> Soon, we will explore how to make the HTML file as template and inject source code from Activity or Fragment.

### 3. Load the static HTML on `WebView`
Finally on your Activity or Fragment, once view is loaded initialize `WebView` with local html file from `assets`.

```kotlin
webView.apply {
    settings.javaScriptEnabled = true
    webChromeClient = WebViewChromeClient()
    webViewClient = AppWebViewClient()
    loadUrl("file:///android_asset/code-highlight.html")
}
``` 

#### Screenshot
Here is a screenshot taken from a demo static html page that has syntax highlighting using Prism JS.

| ![device-2020-07-18-092715](https://user-images.githubusercontent.com/99822/87853541-fc52d700-c8d8-11ea-9dc6-2d4c624f3b74.png) | ![device-2020-07-18-092727](https://user-images.githubusercontent.com/99822/87853542-fceb6d80-c8d8-11ea-9641-4ecf927b5a01.png) | ![device-2020-07-18-092736](https://user-images.githubusercontent.com/99822/87853543-fe1c9a80-c8d8-11ea-9e11-c9779202368e.png) |
| --- | --- | --- |