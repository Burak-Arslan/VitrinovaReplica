package com.example.androidcase.ui.fragment.collections


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
import com.example.androidcase.data.local.model.VitrinResponseCollection
import com.example.androidcase.ui.fragment.home.adapter.HomeCollectionRecyclervAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_collections.*


@AndroidEntryPoint
class CollectionsFragment : BaseFragment() {

    private var collectionList: List<VitrinResponseCollection?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_collections, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        init()
        events()
        setCollectionList()
    }

    fun init() {
        collectionList = arguments?.get("collectionsList") as List<VitrinResponseCollection?>?
    }

    fun events(){
        imgBackCollections.setOnClickListener {
            findNavController().navigate(R.id.action_collectionsFragment_to_homeFragment)
        }
    }

    private fun setCollectionList() {
        try {
            recyclerCollections.adapter?.notifyDataSetChanged()
            recyclerCollections.adapter = HomeCollectionRecyclervAdapter(
                collectionList,
                requireContext()
            )
            val layoutManagerCollection = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerCollections.layoutManager = layoutManagerCollection
            recyclerCollections.setHasFixedSize(true)

        }catch (ex:Exception){
            Log.e("setCollectionList",ex.message.toString())
        }
    }
}