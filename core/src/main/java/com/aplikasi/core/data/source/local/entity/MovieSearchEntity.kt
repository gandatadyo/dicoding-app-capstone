package com.aplikasi.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_search")
data class MovieSearchEntity(
    @PrimaryKey
    @ColumnInfo(name = "IDMovie") var IDMovie: String,
    @ColumnInfo(name = "Name") var Name: String,
    @ColumnInfo(name = "Description") var Description: String,
    @ColumnInfo(name = "Img") var Img: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean,
) : Parcelable
