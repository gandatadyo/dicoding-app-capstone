package com.aplikasi.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val idMovie: String,
    val name: String,
    val description: String,
    val image: String,
    var isFavorite: Boolean
) : Parcelable