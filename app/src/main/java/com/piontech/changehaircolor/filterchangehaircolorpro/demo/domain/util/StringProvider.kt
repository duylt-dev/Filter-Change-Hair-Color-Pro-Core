package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.util

import androidx.annotation.StringRes

/** Resolves Android string resources for the ViewModels without coupling them to a Context. */
interface StringProvider {
    fun get(@StringRes resId: Int, vararg args: Any): String
}
