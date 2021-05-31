package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseCollection {
    @SerializedName("id")
    @Expose
    public var id: Int? = null

    @SerializedName("title")
    @Expose
    public var title: String? = null

    @SerializedName("definition")
    @Expose
    public var definition: String? = null

    @SerializedName("start")
    @Expose
    public var start: String? = null

    @SerializedName("end")
    @Expose
    public var end: Any? = null

    @SerializedName("share_url")
    @Expose
    public var shareUrl: String? = null

    @SerializedName("cover")
    @Expose
    public var cover: VitrinResponseFeaturedCover? = null

    @SerializedName("logo")
    @Expose
    public var logo: VitrinResponseLogo? = null
}