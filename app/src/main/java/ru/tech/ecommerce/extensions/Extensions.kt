package ru.tech.ecommerce.extensions

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.widget.ImageViewCompat

object Extensions {

    fun ImageView.setTint(@ColorInt color: Int?) {
        if (color != null) {
            ImageViewCompat.setImageTintMode(this, PorterDuff.Mode.SRC_ATOP)
            ImageViewCompat.setImageTintList(
                this,
                ColorStateList.valueOf(color)
            )
        } else ImageViewCompat.setImageTintList(this, null)
    }

}