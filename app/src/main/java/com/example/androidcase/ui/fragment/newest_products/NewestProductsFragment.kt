package com.example.androidcase.ui.fragment.newest_products


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidcase.R
import com.example.androidcase.base.BaseFragment
import com.example.androidcase.data.local.model.VitrinResponseProduct
import com.example.androidcase.ui.fragment.home.adapter.HomeProductRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_newest_products.*


@AndroidEntryPoint
class NewestProductsFragment : BaseFragment() {

    private var newestProductList: List<VitrinResponseProduct?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_newest_products, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        init()
        events()
        setRecyclerNewesProducts()
    }

    fun init() {
        newestProductList = arguments?.get("newestProductValue") as List<VitrinResponseProduct?>?
    }

    fun events(){
        imgBackNewestProduct.setOnClickListener {
            findNavController().navigate(R.id.action_newestProductsFragment_to_homeFragment)
        }
    }

    private fun setRecyclerNewesProducts() {
        try {
            recyclerNewestProducts.adapter?.notifyDataSetChanged()
            recyclerNewestProducts.adapter = HomeProductRecyclerAdapter(
                newestProductList,
                requireContext()
            )
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerNewestProducts.layoutManager = layoutManager
            recyclerNewestProducts.setHasFixedSize(true)
        }catch (ex:Exception){
            Log.e("recyclerNewesProducts",ex.message.toString())
        }
    }
}