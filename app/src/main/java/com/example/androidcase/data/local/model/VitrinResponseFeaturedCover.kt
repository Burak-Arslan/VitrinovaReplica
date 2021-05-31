package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseFeaturedCover {
    @SerializedName("width")
    @Expose
    private var width: Int? = null

    @SerializedName("height")
    @Expose
    private var height: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("medium")
    @Expose
    private var medium: VitrinResponseFeaturedCoverMedium? = null

    @SerializedName("thumbnail")
    @Expose
    private var thumbnail: VitrinResponseFeaturedCoverThumbnail? = null
}