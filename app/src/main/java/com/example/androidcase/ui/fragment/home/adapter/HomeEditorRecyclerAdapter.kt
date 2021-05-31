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
import kotlinx.android.synthetic.main.recycler_item_editor.view.*

class HomeEditorRecyclerAdapter (
    private val editor: List<VitrinResponseShop?>?,
    private val context: Context
) :
    RecyclerView.Adapter<HomeEditorRecyclerAdapter.EditorViewHolder>() {

    private var screenWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorViewHolder {
        val displayMetrics = context.resources.displayMetrics
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getRealMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_editor, parent, false)
        return EditorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return editor?.size!!
    }


    override fun onBindViewHolder(holder: EditorViewHolder, position: Int) {
        try {
            val currentItem = editor?.get(position)

            holder.itemView.txtEditorTitle.text = currentItem?.name ?: ""
            holder.itemView.txtEditorSub.text = currentItem?.definition

            loadPhoto(currentItem?.logo?.url.toString(),holder.itemView.imgLogo)
            loadPhoto(currentItem?.productList?.get(0)?.images?.get(0)?.url.toString(),holder.itemView.imgOne)
            loadPhoto(currentItem?.productList?.get(0)?.images?.get(1)?.url.toString(),holder.itemView.imgTwo)
            loadPhoto(currentItem?.productList?.get(0)?.images?.get(2)?.url.toString(),holder.itemView.imgThree)

        } catch (e: Exception) {
            Log.e("HomeProductViewHolder", e.message.toString())
        }
    }


    fun loadPhoto(url:String,img:ImageView){
        try{
            Glide
                .with(context)
                .load(url)
                .into(img)
        }catch (ex:Exception){
            Log.e("loadPhotoEditor",ex.message.toString())
        }
    }

    inner class EditorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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