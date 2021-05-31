package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseCategorry {
    @SerializedName("id")
    @Expose
    public var id: Int? = null

    @SerializedName("name")
    @Expose
    public var name: String? = null

    @SerializedName("parent_id")
    @Expose
    public var parentId: Int? = null

    @SerializedName("order")
    @Expose
    public var order: Int? = null

    @SerializedName("parent_category")
    @Expose
    public var parentCategory: VitrinResponseParentCategory? = null

    @SerializedName("logo")
    @Expose
    public var logo: VitrinResponseLogo? = null
}