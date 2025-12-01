# Copilot Instructions for Android Syntax Highlighter

## Repository Summary

This is an Android library project that demonstrates how to integrate web-based syntax highlighting (PrismJS and highlight.js) into Android applications using WebView. It supports Android Legacy Views, Fragments, and Jetpack Compose. The project is **not** a published library—it's a reference implementation for developers to copy and adapt.

## Project Overview

- **Language**: Kotlin (primary), Java (minimal)
- **Build System**: Gradle 8.11.1 with Android Gradle Plugin 8.5.2
- **Min SDK**: 29 (Android 10), Target SDK: 35 (Android 15)
- **Java Version**: 17 (required)
- **Kotlin Version**: 2.0.21
- **Architecture**: Multi-module Gradle project with two modules

## Build Commands

**Always use the Gradle wrapper** (`./gradlew`) for builds. Do not use system Gradle.

| Task | Command | Description |
|------|---------|-------------|
| Build All | `./gradlew build` | Compiles both modules, runs unit tests, generates APK |
| Clean Build | `./gradlew clean build` | Clean and rebuild from scratch |
| Unit Tests | `./gradlew test` | Run unit tests only |
| Lint | `./gradlew lint` | Run Android Lint checks |
| Assemble Debug | `./gradlew assembleDebug` | Build debug APK only |
| Assemble Release | `./gradlew assembleRelease` | Build release APK only |

**Build time**: First build takes 2-3 minutes (downloads Gradle and dependencies). Subsequent builds are faster with configuration cache enabled.

## Project Structure

```
android-syntax-highlighter/
├── highlighter/           # Library module (reusable syntax highlighting components)
│   ├── build.gradle       # Library build config
│   └── src/main/
│       ├── assets/www/    # PrismJS & highlight.js files (prism.js, prism.css, etc.)
│       └── java/dev/hossain/ynaash/
│           ├── compose/   # SyntaxHighlighterComposable.kt (Jetpack Compose)
│           ├── prismjs/   # PrismJS implementation (Fragment, WebView, Template)
│           ├── highlightjs/ # highlight.js implementation
│           └── webclient/ # WebView client utilities
├── example/               # Demo application module
│   ├── build.gradle       # App build config
│   └── src/main/java/dev/hossain/ynaash/example/
│       ├── SampleApp.kt   # Application class
│       └── ui/            # Demo activities (MainActivity, PrismJsDemoActivity, etc.)
├── build.gradle           # Root project build file (defines shared versions)
├── settings.gradle        # Module includes
├── gradle.properties      # Build configuration
└── gradle/wrapper/        # Gradle wrapper (8.11.1)
```

## Key Source Files

| File | Purpose |
|------|---------|
| `highlighter/src/main/java/dev/hossain/ynaash/prismjs/SyntaxHighlighterWebView.kt` | Main custom WebView for PrismJS |
| `highlighter/src/main/java/dev/hossain/ynaash/prismjs/SyntaxHighlighterFragment.kt` | Fragment-based implementation |
| `highlighter/src/main/java/dev/hossain/ynaash/prismjs/PrismJsHtmlTemplate.kt` | HTML template function |
| `highlighter/src/main/java/dev/hossain/ynaash/compose/SyntaxHighlighterComposable.kt` | Jetpack Compose wrapper |
| `example/src/main/java/dev/hossain/ynaash/example/ui/MainActivity.kt` | Demo app entry point |

## Configuration Files

| File | Purpose |
|------|---------|
| `build.gradle` (root) | Defines Kotlin version, Android Gradle Plugin, shared dependency versions |
| `gradle.properties` | JVM args, AndroidX settings, configuration cache enabled |
| `settings.gradle` | Defines modules: `:highlighter` and `:example` |
| `.github/workflows/android.yml` | CI workflow (build on push/PR to main/develop) |

## CI Workflow

The GitHub Actions workflow (`.github/workflows/android.yml`) runs on:
- Push to `main` branch
- Pull requests to `main` or `develop` branches

**CI Steps**:
1. Checkout code
2. Setup Java 17 (Zulu distribution)
3. Setup Gradle
4. Run `./gradlew build`

**To validate changes locally before pushing**, run: `./gradlew build`

## Dependencies (defined in root build.gradle `ext` block)

All version numbers are centralized in the root `build.gradle` file under the `ext` block (not using version catalogs):
- `appCompatVersion = '1.7.0'`
- `coreKtxVersion = '1.15.0'`
- `composeBomVersion = '2024.12.01'`
- `junitVersion = '4.13.2'`
- `espressoCoreVersion = '3.6.1'`
- `timberLibraryVersion = '5.0.1'`

## Testing

- **Unit tests**: Located in `*/src/test/java/` directories
- **Instrumented tests**: Located in `*/src/androidTest/java/` directories
- Tests are minimal (example placeholder tests exist)
- Run unit tests: `./gradlew test`
- Run instrumented tests: `./gradlew connectedAndroidTest` (requires Android emulator/device)

## Code Style

- Kotlin code style: `official` (set in gradle.properties)
- No custom linting rules configured
- Standard Android/Kotlin conventions apply

## Important Notes

1. **JavaScript is enabled** in WebViews (`@SuppressLint("SetJavaScriptEnabled")`) - this is intentional for syntax highlighting functionality.

2. **Web assets location**: All JS/CSS files for syntax highlighting are in `highlighter/src/main/assets/www/`.

3. **No library publication**: This project is not published to Maven. It's reference code to copy.

4. **Compose support**: Uses Compose BOM for version management. The `SyntaxHighlighter` composable wraps the WebView implementation.

5. **Two syntax highlighters**: Both PrismJS (`prismjs/`) and highlight.js (`highlightjs/`) implementations exist. PrismJS is the primary/recommended one.

## Trust These Instructions

These instructions have been validated against the actual repository structure. Only search for additional information if:
- The build commands fail with unexpected errors
- Files mentioned do not exist
- You need details about a specific implementation not covered here
