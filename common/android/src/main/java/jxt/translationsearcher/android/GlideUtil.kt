package jxt.translationsearcher.android

import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun RequestBuilder<Drawable>.progressBarView(progress: ProgressBar): RequestBuilder<Drawable> {
    progress.isVisible = true
    
    return addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
        ): Boolean {
            progress.isGone = true
            return false
        }

        override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
        ): Boolean {
            progress.isGone = true
            return false
        }

    })
}