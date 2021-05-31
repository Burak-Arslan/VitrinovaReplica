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
import com.example.androidcase.data.local.model.VitrinResponseCategorry
import kotlinx.android.synthetic.main.recycler_item_category.view.*

class HomeCategoryRecyclerAdapter(
    private val category: List<VitrinResponseCategorry?>?,
    private val context: Context
) :
    RecyclerView.Adapter<HomeCategoryRecyclerAdapter.CategoryViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return category?.size!!
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        try {
            setFadeAnimation(holder.itemView.cardCategory, position)

            val currentItem = category?.get(position)
            holder.itemView.txtCategory.text = currentItem?.name ?: ""

            Glide
                .with(context)
                .load(currentItem?.logo?.url)
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


    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }
}