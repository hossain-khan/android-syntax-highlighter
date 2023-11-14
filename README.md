# android-syntax-highlighter
yet _NOT_ another android syntax highlighter (YNAASH)

### Objective
Explore well established web based syntax highlighter like [PrismJS](https://prismjs.com/) and [highlight.js](https://highlightjs.org/), 
and showcase how anybody can quickly incorporate these into their project by following some examples provided here.


> The intention is **NOT** to create another library project that gets abandoned over time.
Feel free to copy parts of code that is necessary for you to add syntax highlighting support to your app.

* Try out the latest [release](https://github.com/amardeshbd/android-syntax-highlighter/releases) - `syntax-highlighter-example-v1.1.apk` _(2.5 MB)_ [[Download](https://github.com/amardeshbd/android-syntax-highlighter/releases/download/v1.1/syntax-highlighter-example-v1.1.apk)] 


## Existing Syntax Highlighting Libraries

If you need a library, you may look into following existing projects 

1. [CodeView-Android](https://github.com/kbiakov/CodeView-Android) - Display code with syntax highlighting ✨ in native way.  
 845 :star:, Last updated: Jan 24, 2019
1. [highlightjs-android](https://github.com/PDDStudio/highlightjs-android) - A view for source code syntax highlighting on Android.  
 310 :star:, Last updated: Aug 19, 2020
1. [Syntax-View-Android](https://github.com/Badranh/Syntax-View-Android) - Beautiful Android Syntax View with line counter it will automatically highlight the code.  
 56 :star:, Last updated: Mar 24, 2020
1. [KodeEditor](https://github.com/markusressel/KodeEditor) - A simple code editor with syntax highlighting and pinch to zoom.  
 72 :star:, Last updated: May 19, 2023
1. [HighlightJs View - Android](https://github.com/PDDStudio/highlightjs-android) - A view for source code syntax highlighting on Android.  
 310 :star:, Last updated: Aug 19, 2020
1. [synta kt s](https://github.com/wingio/syntakts) - Simple to use text parser and syntax highlighter for Kotlin Multiplatform.  
 7 :star:, Last updated: Nov 11, 2023 _(Actively beind developed with KMP focus)_

> _NOTE: The 'Last updated' and :star: data was taken as of Nov 13th, 2023_

------------------------

- [Under the hood](#under-the-hood)
  - [1. Choose JS Library](#1-choose-js-library)
  - [2. Use HTML+CSS+JS Asset](#2-use-htmlcssjs-asset)
  - [3. Load the static HTML on `WebView`](#3-load-the-static-html-on-webview)
    - [Example App Screenshots](#screenshot)
- [Building your own Fragment or Custom View](#building-your-own-fragment-or-custom-view)
  - [Custom View](#custom-view)
    - [PrismJS Template Function](#prismjs-template-function)
    - [Creating custom syntax highlighter WebView](#creating-custom-syntax-highlighter-webview)
    - [Use custom view from Fragment or Activity](#use-custom-view-from-fragment-or-activity)
  - [Fragment](#fragment)
    - [Create custom Syntax Highlighter Fragment](#create-custom-syntax-highlighter-fragment)
    - [Using the Syntax Highlighter Fragment](#using-the-syntax-highlighter-fragment)

## Under the hood
Here is how you would have syntax highlighting using any modern JavaScript library.

> ps. I also ✍️ wrote a [short blog](https://medium.com/@hossainkhan/source-code-syntax-highlighting-on-android-taking-full-control-b704fd4bd8ee) summarizing the process on Medium.com

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
> See [Custom View](#building-your-own-fragment-or-custom-view) section below for detailed instructions.

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

## Building your own Fragment or Custom View
Ideally, there should be a modular component or custom-view that you **re-use** syntax highlighting with dynamic content.
For that having a `Fragment` or custom `View` is ideal.

We can taken the learning from [above](#under-the-hood) to wrap the JavaScript based syntax highlighting library 
in fragment or custom view using `WebView`. Both comes with advantage of it's own.
Regardless if which option is chosen, the underlying code is _almost_ identical.

### Custom View
The advantage of custom view is that, it can be used anywhere, `Activity` or `Fragment`. 
Let's take a look how we can templatize the HTML to load source code dynamically.

In this case, all we need to do is move the [html content defined above](#2-use-htmlcssjs-asset) to a `String` variable with options you need.

#### PrismJS Template Function
```kotlin
fun prismJsHtmlContent(
    formattedSourceCode: String,
    language: String,
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
</head>
<body>
<pre class="${if (showLineNumbers) "line-numbers" else ""}">
<code class="language-${language}">${formattedSourceCode}</code>
</pre>
</body>
</html>
"""
}
```

In this example, we have `showLineNumbers` as optional parameter, likewise we could have line number parameter to highlight a line or section.
PrismJS has [dozens of plugins](https://prismjs.com/download.html) that you can use and expose those options though this function.

#### Creating custom syntax highlighter WebView
Here you just need to extend the `WebView` and expose a function `bindSyntaxHighlighter()` to send source code and configurations.
```kotlin
class SyntaxHighlighterWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {
    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun bindSyntaxHighlighter(
        formattedSourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ) {
        settings.javaScriptEnabled = true
        webChromeClient = WebViewChromeClient()
        webViewClient = AppWebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH /* baseUrl */,
            prismJsHtmlContent(formattedSourceCode, language, showLineNumbers) /* html-data */,
            "text/html" /* mimeType */,
            "utf-8" /* encoding */,
            "" /* failUrl */
        )
    }
}
```

#### Use custom view from Fragment or Activity

Using the newly defined `SyntaxHighlighterWebView` in `Fragment` or `Activity` is business as usual that you are used to.

In your Layout XML file, add the view with proper layout parameters.

```xml
<your.prismjs.SyntaxHighlighterWebView
    android:id="@+id/syntax_highlighter_webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

When `Fragment` or `Activity` is loaded, get reference to `SyntaxHighlighterWebView` and bind it with source code.
```kotlin
val syntaxHighlighter = findViewById(R.id.syntax_highlighter_webview)

syntaxHighlighter.bindSyntaxHighlighter(
    formattedSourceCode = "data class Student(val name: String)",
    language = "kotlin"
)
```

That's it, you have re-usable custom view that you can use anywhere in the layout.

## Fragment
`Fragment` is a modular UI component that can be use in a `Activity`. It comes with it's own flexibility like:
* Being able to replace whole screen content
* Replace only part of the content
* Use with navigation library as destination
* and so on

In this case `Fragment` is just a shell that loads `WebView` and configures it such that it 
can show syntax highlighted source code.

### Create custom Syntax Highlighter Fragment

Based on Android [official guide](https://developer.android.com/guide/components/fragments#Example) 
we need to pass all the required data needed for `prismJsHtmlContent` function [defined above](#prismjs-template-function)
```kotlin
fun newInstance(
    formattedSourceCode: String,
    language: String,
    showLineNumbers: Boolean = false
) = SyntaxHighlighterFragment().apply {
    arguments = Bundle().apply {
        putString(ARG_KEY_SOURCE_CODE_CONTENT, formattedSourceCode)
        putString(ARG_KEY_CODE_LANGUAGE, language)
        putBoolean(ARG_KEY_SHOW_LINE_NUMBERS, showLineNumbers)
    }
}
```

> See [SyntaxHighlighterFragment.kt](https://github.com/amardeshbd/android-syntax-highlighter/blob/develop/highlighter/src/main/java/dev/hossain/yaash/prismjs/SyntaxHighlighterFragment.kt)
> source code for full example.

And finally when `Fragment#onViewCreated()` is called, we use the extracted the bundle parameters 
to initialize and load the syntax highlighting.

```kotlin
@SuppressLint("SetJavaScriptEnabled")
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Loads the plain `WebView` defined in `fragment_highlighter.xml`
    val webView: WebView = view.findViewById(R.id.web_view)

    webView.apply {
        settings.javaScriptEnabled = true
        webChromeClient = WebViewChromeClient()
        webViewClient = AppWebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH /* baseUrl */,
            prismJsHtmlContent(sourceCode, language, showLineNumbers) /* html-data */,
            "text/html" /* mimeType */,
            "utf-8" /* encoding */,
            "" /* failUrl */
        )
    }
}
```

### Using the Syntax Highlighter Fragment
From your `Activity` or `Fragment`, create an instance of `SyntaxHighlighterFragment` and add that
to fragment container on the screen.
```kotlin
val fragment = SyntaxHighlighterFragment.newInstance(
    formattedSourceCode = "data class Student(val name: String)",
    language = "kotlin",
    showLineNumbers = true
)
```

> See [PrismJsDemoActivity.kt](https://github.com/amardeshbd/android-syntax-highlighter/blob/develop/example/src/main/java/dev/hossain/yaash/example/ui/demoprismjs/PrismJsDemoActivity.kt)
> source code for full example.
