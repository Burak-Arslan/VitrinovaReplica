package com.example.androidcase.ui.fragment.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcase.R
import com.example.androidcase.data.local.model.VitrinResponseShop
import kotlinx.android.synthetic.main.recycler_item_new_vitrin.view.*

class HomeNewVitrinAdapter(
    private val newVitrin: List<VitrinResponseShop?>?,
    private val context: Context,
    private val homeFragment: Boolean
) :
    RecyclerView.Adapter<HomeNewVitrinAdapter.NewVitrinViewHolder>() {

    private var screenWidth = 0
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewVitrinViewHolder {
        val displayMetrics = context.resources.displayMetrics
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getRealMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_new_vitrin, parent, false)
        return NewVitrinViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newVitrin?.size!!
    }

    override fun onBindViewHolder(holder: NewVitrinViewHolder, position: Int) {
        try {
            setFadeAnimation(holder.itemView.cardNewItem, position)
            val currentItem = newVitrin?.get(position)

            if (homeFragment) {
                val itemWidth = screenWidth / 1.5
                val lp = holder.itemView.layoutParams
                lp.height = lp.height
                lp.width = itemWidth.toInt()
                holder.itemView.layoutParams = lp
            }

            if (currentItem?.cover?.url != null) {
                loadPhoto(currentItem?.cover?.url.toString(), holder.itemView.imgNewVitrin)
            } else {
                holder.itemView.imgNewVitrin.setBackgroundResource(R.drawable.no_pic);
            }

            holder.itemView.txtLogo.text = currentItem?.name?.substring(0, 2) ?: ""

            holder.itemView.txtNewVitrinTitle.text = currentItem?.name ?: ""
            holder.itemView.txtNewVitrinSubTitle.text = currentItem?.definition ?: ""
            holder.itemView.txtNewVitrinShopCount.setText(
                context.getString(
                    R.string.item,
                    currentItem?.productCount
                )
            )

        } catch (e: Exception) {
            Log.e("HomeProductViewHolder", e.message.toString())
        }
    }

    fun setFadeAnimation(view: View, position: Int) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.item_animation_from_right)
        view.startAnimation(anim)
        lastPosition = position
    }

    fun loadPhoto(url: String, img: ImageView) {
        try {
            Glide
                .with(context)
                .load(url)
                .into(img)
        } catch (ex: Exception) {
            Log.e("loadPhotoEditor", ex.message.toString())
        }
    }

    inner class NewVitrinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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