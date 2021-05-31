package com.example.androidcase.ui.fragment.newest_showcase


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcase.R
import com.example.androidcase.base.BaseFragment
import com.example.androidcase.data.local.model.VitrinResponseShop
import com.example.androidcase.ui.fragment.home.adapter.HomeNewVitrinAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_newest_show_case.*


@AndroidEntryPoint
class NewestShowcaseFragment : BaseFragment() {

    private var newestShowcaseList: List<VitrinResponseShop?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_newest_show_case, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        init()
        events()
        setRecyclerNewesProducts()
    }

    fun init() {
        newestShowcaseList = arguments?.get("newestShowcaseList") as List<VitrinResponseShop?>?
    }

    fun events(){
        imgNewestShowcase.setOnClickListener {
            findNavController().navigate(R.id.action_newestShowcaseFragment_to_homeFragment)
        }
    }

    private fun setRecyclerNewesProducts() {
        try {
            recyclerimgNewestShowcase.adapter?.notifyDataSetChanged()
            recyclerimgNewestShowcase.adapter = HomeNewVitrinAdapter(
                newestShowcaseList,
                requireContext(),
                false
            )
            val layoutManagerNewVitrin = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerimgNewestShowcase.layoutManager = layoutManagerNewVitrin
            recyclerimgNewestShowcase.setHasFixedSize(true)
        }catch (ex:Exception){
            Log.e("recyclerNewesProducts",ex.message.toString())
        }
    }
}