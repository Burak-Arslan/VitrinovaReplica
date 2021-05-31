package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseParentCategory {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("parent_id")
    @Expose
    private var parentId: Any? = null

    @SerializedName("order")
    @Expose
    private var order: Int? = null

    @SerializedName("parent_category")
    @Expose
    private var parentCategory: Any? = null
}