package com.example.androidcase.ui.fragment.editor_choice


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidcase.R
import com.example.androidcase.base.BaseFragment
import com.example.androidcase.data.local.model.VitrinResponseProduct
import com.example.androidcase.data.local.model.VitrinResponseShop
import com.example.androidcase.ui.fragment.home.adapter.HomeEditorRecyclerAdapter
import com.example.androidcase.ui.fragment.home.adapter.HomeProductRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_editor_choice.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recyclerProducts
import kotlinx.android.synthetic.main.fragment_newest_products.*


@AndroidEntryPoint
class EditorChoiceFragment : BaseFragment() {

    private var editorChoiceList: List<VitrinResponseShop?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_editor_choice, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        init()
        events()
        setEditorChoiseList()
    }

    fun init() {
        editorChoiceList = arguments?.get("editorChoiceList") as List<VitrinResponseShop?>?
    }

    fun events(){
        imgBackEditorChoice.setOnClickListener {
            findNavController().navigate(R.id.action_editorChoiceFragment_to_homeFragment)
        }
    }

    private fun setEditorChoiseList() {
        try {
            recyclerEditorChoice.adapter?.notifyDataSetChanged()
            recyclerEditorChoice.adapter = HomeEditorRecyclerAdapter(
                editorChoiceList,
                requireContext()
            )
            val layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerEditorChoice.layoutManager = layoutManager
            recyclerEditorChoice.setHasFixedSize(true)
        }catch (ex:Exception){
            Log.e("recyclerNewesProducts",ex.message.toString())
        }
    }
}