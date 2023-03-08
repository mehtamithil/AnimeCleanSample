package com.anime_clean_sample.resource

import android.content.Context
import androidx.annotation.StringRes

sealed interface UiText {

    data class StringText(
        val text: String
    ) : UiText

    class StringResourceText(
        @StringRes val textResId: Int,
        vararg val args: Any
    ) : UiText

    fun asString(context: Context) = when (this) {
        is StringText -> text
        is StringResourceText -> context.getString(textResId, *args)
    }

}
