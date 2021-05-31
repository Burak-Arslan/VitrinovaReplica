package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseProduct {

    @SerializedName("id")
    @Expose
    public var id: Int? = null

    @SerializedName("code")
    @Expose
    public var code: Any? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("slug")
    @Expose
    public var slug: String? = null

    @SerializedName("definition")
    @Expose
    public var definition: String? = null

    @SerializedName("old_price")
    @Expose
    var oldPrice: Int? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("stock")
    @Expose
    public var stock: Int? = null

    @SerializedName("max_installment")
    @Expose
    public var maxInstallment: Any? = null

    @SerializedName("commission_rate")
    @Expose
    public var commissionRate: Int? = null

    @SerializedName("cargo_time")
    @Expose
    public var cargoTime: Int? = null

    @SerializedName("is_cargo_free")
    @Expose
    public var isCargoFree: Boolean? = null

    @SerializedName("is_new")
    @Expose
    public var isNew: Boolean? = null

    @SerializedName("reject_reason")
    @Expose
    public var rejectReason: Any? = null

    @SerializedName("category_id")
    @Expose
    public var categoryId: Int? = null

    @SerializedName("difference")
    @Expose
    public var difference: String? = null

    @SerializedName("is_editor_choice")
    @Expose
    public var isEditorChoice: Boolean? = null

    @SerializedName("comment_count")
    @Expose
    public var commentCount: Int? = null

    @SerializedName("is_owner")
    @Expose
    public var isOwner: Boolean? = null

    @SerializedName("is_approved")
    @Expose
    public var isApproved: Boolean? = null

    @SerializedName("is_active")
    @Expose
    public var isActive: Boolean? = null

    @SerializedName("share_url")
    @Expose
    public var shareUrl: String? = null

    @SerializedName("is_liked")
    @Expose
    public var isLiked: Boolean? = null

    @SerializedName("like_count")
    @Expose
    public var likeCount: Int? = null

    @SerializedName("shop")
    @Expose
    var shop: VitrinResponseShop? = null

    @SerializedName("category")
    @Expose
    public var category: VitrinResponseCategorry? = null

    @SerializedName("images")
    @Expose
    public var images: List<VitrinResponseProductImage?>? = null

}