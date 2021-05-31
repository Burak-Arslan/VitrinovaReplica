package com.example.androidcase.ui.fragment.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcase.R
import com.example.androidcase.data.local.model.VitrinResponseFetaured
import kotlinx.android.synthetic.main.item_home_viewpager.view.*


class HomeViewPagerAdapter(
    private val featured: List<VitrinResponseFetaured?>?,
    private val requireContext: Context
) :
    RecyclerView.Adapter<HomeViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_viewpager, parent, false)
        return ViewPagerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if(featured != null){
            return featured?.size!!
        }else{
            return 0
        }
    }


    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        try {
            val currentItem = featured?.get(position)
            holder.itemView.txtTitle.text = currentItem?.title ?: ""
            holder.itemView.txtSubTitle.text = currentItem?.subTitle ?: ""
            Glide
                .with(requireContext)
                .load(currentItem?.cover?.url)
                .into(holder.itemView.imgViewPager)

        } catch (e: Exception) {
            Log.e("PhotoGalleryViewHolder", e.message.toString())
        }
    }


    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //listener.onItemClick("",imgURL)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(target: String, notifID: String)
    }
}