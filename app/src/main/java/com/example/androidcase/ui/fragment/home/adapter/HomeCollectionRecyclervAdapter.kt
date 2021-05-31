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
import com.example.androidcase.data.local.model.VitrinResponseCollection

import kotlinx.android.synthetic.main.recycler_item_collection.view.*
import kotlinx.android.synthetic.main.recycler_item_collection.view.imgCategory

class HomeCollectionRecyclervAdapter (
    private val collection: List<VitrinResponseCollection?>?,
    private val context: Context
) :
    RecyclerView.Adapter<HomeCollectionRecyclervAdapter.CollectionViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_collection, parent, false)
        return CollectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return collection?.size!!
    }


    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        try {
            setFadeAnimation(holder.itemView.cardCollection, position)

            val currentItem = collection?.get(position)
            holder.itemView.txtCategoryTitle.text = currentItem?.title ?: ""
            holder.itemView.txtCategorySubTitle.text = currentItem?.definition ?: ""

            Glide
                .with(context)
                .load(currentItem?.cover?.url)
                .into(holder.itemView.imgCategory)

        } catch (e: Exception) {
            Log.e("HomeProductViewHolder", e.message.toString())
        }
    }

    fun setFadeAnimation(view: View, position: Int) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.item_animation_from_right)
        view.startAnimation(anim)
        lastPosition = position
    }


    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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