package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponse {

    @SerializedName("type")
    @Expose
    public var type: String? = null

    @SerializedName("title")
    @Expose
    public var title: String? = null

    @SerializedName("featured")
    @Expose
    public var featured: List<VitrinResponseFetaured?>? = null

    @SerializedName("products")
    @Expose
    public var products: List<VitrinResponseProduct?>? = null

    @SerializedName("categories")
    @Expose
    public var categories: List<VitrinResponseCategorry?>? = null

    @SerializedName("collections")
    @Expose
    public var collections: List<VitrinResponseCollection?>? = null

    @SerializedName("shops")
    @Expose
    public var shops: List<VitrinResponseShop?>? = null
}