package com.jay.navermovie.data.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jay.navermovie.data.search.Movie

data class NaverResult(
    @SerializedName("display")
    @Expose
    val display: Int,

    @SerializedName("items")
    @Expose
    val items: List<Movie>,

    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,

    @SerializedName("start")
    @Expose
    val start: Int,

    @SerializedName("total")
    @Expose
    val total: Int
)