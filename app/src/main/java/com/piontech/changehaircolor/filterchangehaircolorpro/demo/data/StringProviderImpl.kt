package com.piontech.changehaircolor.filterchangehaircolorpro.demo.data

import android.content.Context
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.util.StringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : StringProvider {
    override fun get(resId: Int, vararg args: Any): String =
        if (args.isEmpty()) context.getString(resId) else context.getString(resId, *args)
}
