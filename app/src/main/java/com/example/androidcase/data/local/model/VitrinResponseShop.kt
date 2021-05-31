package com.example.androidcase.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VitrinResponseShop {
    @SerializedName("id")
    @Expose
    public var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    public var slug: String? = null

    @SerializedName("definition")
    @Expose
    public var definition: String? = null

    @SerializedName("name_updateable")
    @Expose
    public var nameUpdateable: Boolean? = null

    @SerializedName("vacation_mode")
    @Expose
    public var vacationMode: Int? = null

    @SerializedName("created_at")
    @Expose
    public var createdAt: String? = null

    @SerializedName("shop_payment_id")
    @Expose
    public var shopPaymentId: Int? = null

    @SerializedName("product_count")
    @Expose
    public var productCount: Int? = null

    @SerializedName("shop_rate")
    @Expose
    public var shopRate: Int? = null

    @SerializedName("comment_count")
    @Expose
    public var commentCount: Int? = null

    @SerializedName("follower_count")
    @Expose
    public var followerCount: Int? = null

    @SerializedName("is_editor_choice")
    @Expose
    public var isEditorChoice: Boolean? = null

    @SerializedName("is_following")
    @Expose
    public var isFollowing: Boolean? = null

    @SerializedName("cover")
    @Expose
    public var cover: VitrinResponseFeaturedCover? = null

    @SerializedName("share_url")
    @Expose
    public var shareUrl: String? = null

    @SerializedName("logo")
    @Expose
    public var logo: VitrinResponseLogo? = null

    @SerializedName("popular_products")
    @Expose
    public var productList: List<VitrinResponseProduct?>? = null

}