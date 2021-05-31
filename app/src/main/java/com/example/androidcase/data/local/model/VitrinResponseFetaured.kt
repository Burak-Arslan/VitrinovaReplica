package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseFetaured {

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("cover")
    @Expose
    var cover: VitrinResponseFeaturedCover? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("sub_title")
    @Expose
    var subTitle: String? = null
}