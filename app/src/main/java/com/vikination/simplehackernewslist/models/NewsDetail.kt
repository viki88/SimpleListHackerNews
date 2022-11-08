package com.vikination.simplehackernewslist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetail(
    val by : String,
    val descendants : Int,
    val id : Int,
    val score : Int,
    val time : Long,
    val title : String,
    val type : String,
    val url : String
) :Parcelable