package com.example.androidcase.ui.fragment.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcase.R
import com.example.androidcase.data.local.model.VitrinResponseProduct

import kotlinx.android.synthetic.main.recycler_item_product.view.*


class HomeProductRecyclerAdapter(
    private val product: List<VitrinResponseProduct?>?,
    private val context: Context
) :
    RecyclerView.Adapter<HomeProductRecyclerAdapter.ProductViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return product?.size!!
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        try {
            setFadeAnimation(holder.itemView.cardProduct, position)

            val currentItem = product?.get(position)
            holder.itemView.txtDescription.text = currentItem?.title ?: ""
            holder.itemView.txtProductDetails.text = currentItem?.definition ?: ""

            if (currentItem?.price != null) {
                holder.itemView.txtNewPrice.text = currentItem?.price.toString() + "" + " TL"

            } else {
                holder.itemView.txtNewPrice.text = ""
            }

            if (currentItem?.oldPrice != null) {
                holder.itemView.txNewOld.text = currentItem?.oldPrice.toString() + "" + " TL"
            } else {
                holder.itemView.txNewOld.text = ""
            }

            Glide
                .with(context)
                .load(currentItem?.images?.get(0)?.url)
                .into(holder.itemView.imgProduct)

        } catch (e: Exception) {
            Log.e("HomeProductViewHolder", e.message.toString())
        }
    }

    fun setFadeAnimation(view: View, position: Int) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.item_animation_from_right)
        view.startAnimation(anim)
        lastPosition = position
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    interface OnItemClickListener {

    }
}