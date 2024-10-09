package com.bluelaned.animelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    var name: String?,
    var author: String?,
    var description: String?,
    var photo: Int?
) :  Parcelable