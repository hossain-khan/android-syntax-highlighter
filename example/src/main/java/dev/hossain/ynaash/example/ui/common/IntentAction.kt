package dev.hossain.ynaash.example.ui.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import timber.log.Timber

object IntentAction {
    /**
     * Loads an external web URL.
     * https://developer.android.com/guide/components/intents-common#ViewUrl
     */
    fun openWebPage(context: Context, url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Timber.w("Unable to find any app that can open web URL")

            // Instead of keeping user in dark, show them feedback about the issue.
            Toast.makeText(
                context,
                "Ops! We're unable to open the URL as we can't find default browser app :-(",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
