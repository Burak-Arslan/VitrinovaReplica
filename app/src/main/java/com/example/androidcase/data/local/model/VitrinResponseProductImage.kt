package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseProductImage {
    @SerializedName("width")
    @Expose
    public var width: Int? = null

    @SerializedName("height")
    @Expose
    public var height: Int? = null

    @SerializedName("url")
    @Expose
    public var url: String? = null

    @SerializedName("medium")
    @Expose
    public var medium: VitrinResponseFeaturedCoverMedium? = null

    @SerializedName("thumbnail")
    @Expose
    public var thumbnail: VitrinResponseFeaturedCoverThumbnail? = null

}