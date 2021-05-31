package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseFeaturedCoverMedium {
    @SerializedName("width")
    @Expose
    private var width: Int? = null

    @SerializedName("height")
    @Expose
    private var height: Int? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null
}