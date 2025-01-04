package com.techetronventures.trek

import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class OnBoardItemModel(
    @RawRes val rawLottie:Int,
    @StringRes val strResTitle:Int,
    @StringRes val strResDescription:Int
)
