package com.example.androidcase.ui.fragment.home.view


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.androidcase.R
import com.example.androidcase.base.BaseFragment
import com.example.androidcase.data.local.model.*
import com.example.androidcase.ui.fragment.home.adapter.*
import com.example.androidcase.ui.fragment.home.view_model.HomeViewModel
import com.example.androidcase.util.SliderTransformer
import com.example.testappp.data.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private val recordAudioRequestCode = 1
    private val RQ_SPEECH_REC = 102

    companion object {
        var editorList: List<VitrinResponseShop?>? = null
        var categoryList: List<VitrinResponseCategorry?>? = null
        var viewPagerList: List<VitrinResponseFetaured?>? = null
        var newestProductList: List<VitrinResponseProduct?>? = null
        var collectionList: List<VitrinResponseCollection?>? = null
        var newestShowcaseList: List<VitrinResponseShop?>? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        init()
        events()
        setupRecyclerviewInit()
        callService()

    }

    private fun checkPermission() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    recordAudioRequestCode
                )
            }
        } catch (ex: Exception) {
            Log.e("checkPermission", ex.message.toString())
        }
    }

    private fun events() {
        mainSwipe.setOnRefreshListener {
            mainSwipe.setRefreshing(false);
            homeViewModel.getAllRestaurantApi.load()
        }

        imgMicrofon.setOnClickListener {
            speechInput()
        }

        pagerVitrinEditor.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Glide
                    .with(requireContext())
                    .load(editorList?.get(position)?.cover?.url)
                    .into(imgVitrinEditor)
            }
        })

        txtNewestProductsAll.setOnClickListener {
            val bundle = bundleOf("newestProductValue" to newestProductList)
            findNavController().navigate(R.id.action_homeFragment_to_newestProductsFragment, bundle)
        }

        txtAllCollections.setOnClickListener {
            val bundle = bundleOf("collectionsList" to collectionList)
            findNavController().navigate(R.id.action_homeFragment_to_collectionsFragment, bundle)
        }

        txtAllEditor.setOnClickListener {
            val bundle = bundleOf("editorChoiceList" to editorList)
            findNavController().navigate(R.id.action_homeFragment_to_editorChoiceFragment, bundle)
        }

        txtAllNewVitrin.setOnClickListener {
            val bundle = bundleOf("newestShowcaseList" to newestShowcaseList)
            findNavController().navigate(R.id.action_homeFragment_to_newestShowcaseFragment, bundle)
        }
    }

    private fun speechInput() {
        try {
            if (!SpeechRecognizer.isRecognitionAvailable(requireContext())) {
                Toast.makeText(requireContext(), "Speech hata!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Konuşunuz")
                startActivityForResult(intent, RQ_SPEECH_REC)
            }
        } catch (ex: Exception) {
            Log.e("speechInput", ex.message.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
                val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                edtSearch.setText(result?.get(0).toString())
            }
        }catch (ex:Exception){
            Log.e("onActivityResult",ex.message.toString())
        }
    }

    private fun setupRecyclerviewInit() {
        setupRecyclerView(recyclerCategory)
        setupRecyclerView(recyclerCollection)
        setupRecyclerView(recyclerNewVitrin)
        setupRecyclerView(recyclerProducts)
    }

    private fun callService() {
        if (editorList == null) {
            homeViewModel.getAllRestaurantApi.load()
        } else {
            setViewPager(null)
            setEditorViewPager(null)
            setProductRecyclerview(null)
            setCategoryRecyclerview(null)
            setCollectionRecyclerview(null)
            setNewVitrinRecyclerview(null)
        }
        homeViewModel.getAllRestaurantApi.state.observe(viewLifecycleOwner, vitrinObserver)
    }

    private fun init() {
        try {
            val c1 = resources.getColor(R.color.red)
            val c2 = resources.getColor(R.color.red)
            val c3 = resources.getColor(R.color.red)
            mainSwipe.setColorSchemeColors(c1, c2, c3)
        } catch (ex: Exception) {
            Log.e("HomeFragmentInit", ex.message.toString())
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        try {
            val animation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_slide_right
            )
            val linearLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.layoutAnimation = animation
            recyclerView.scheduleLayoutAnimation()
        }catch (ex:Exception){
            Log.e("setupRecyclerView",ex.message.toString())
        }
    }

    private fun setNewVitrinRecyclerview(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                recyclerNewVitrin.adapter = HomeNewVitrinAdapter(
                    newestShowcaseList,
                    requireContext(),
                    true
                )
            } else {
                newestShowcaseList = state.data[5].shops
                recyclerNewVitrin.adapter = HomeNewVitrinAdapter(
                    state.data[5].shops,
                    requireContext(),
                    true
                )
            }
            recyclerNewVitrin.adapter?.notifyDataSetChanged()
            val layoutManagerNewVitrin = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerNewVitrin.layoutManager = layoutManagerNewVitrin
            recyclerNewVitrin.setHasFixedSize(true)
        } catch (ex: Exception) {
            Log.e("newVitrinRecyclerview", ex.message.toString())
        }
    }

    private fun setCollectionRecyclerview(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                recyclerCollection.adapter = HomeCollectionRecyclervAdapter(
                    collectionList,
                    requireContext()
                )
            } else {
                collectionList = state.data[3].collections
                recyclerCollection.adapter?.notifyDataSetChanged()
                recyclerCollection.adapter = HomeCollectionRecyclervAdapter(
                    state.data[3].collections,
                    requireContext()
                )
            }
            recyclerCollection.adapter?.notifyDataSetChanged()
            val layoutManagerCollection = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerCollection.layoutManager = layoutManagerCollection
            recyclerCollection.setHasFixedSize(true)

        } catch (ex: Exception) {
            Log.e("collectionRecyclerview", ex.message.toString())
        }
    }

    private fun setCategoryRecyclerview(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                recyclerCategory.adapter = HomeCategoryRecyclerAdapter(
                    categoryList,
                    requireContext()
                )
            } else {
                categoryList = state.data[2].categories
                recyclerCategory.adapter = HomeCategoryRecyclerAdapter(
                    state.data[2].categories,
                    requireContext()
                )
            }
            recyclerCategory.adapter?.notifyDataSetChanged()
            val layoutManagerCategory = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerCategory.layoutManager = layoutManagerCategory
            recyclerCategory.setHasFixedSize(true)
        } catch (ex: Exception) {
            Log.e("setCategoryRecyclerview", ex.message.toString())
        }
    }

    private fun setProductRecyclerview(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                recyclerProducts.adapter = HomeProductRecyclerAdapter(
                    newestProductList,
                    requireContext()
                )
            } else {
                newestProductList = state.data[1].products
                recyclerProducts.adapter?.notifyDataSetChanged()
                recyclerProducts.adapter = HomeProductRecyclerAdapter(
                    state.data[1].products,
                    requireContext()
                )
            }
            recyclerProducts.adapter?.notifyDataSetChanged()
            val layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerProducts.layoutManager = layoutManager
            recyclerProducts.setHasFixedSize(true)
        } catch (ex: Exception) {
            Log.e("ProductRecyclerview", ex.message.toString())
        }
    }

    private fun setViewPager(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                mainViewPager.adapter = HomeViewPagerAdapter(
                    viewPagerList,
                    requireContext()
                )
            } else {
                viewPagerList = state?.data?.get(0)?.featured
                mainViewPager.adapter = HomeViewPagerAdapter(
                    state?.data?.get(0)?.featured,
                    requireContext()
                )
            }
            mainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            circleDot.setViewPager(mainViewPager)
            if (state == null) {
                mainViewPager.setPageTransformer(SliderTransformer(viewPagerList?.size!!))
            } else {
                mainViewPager.setPageTransformer(SliderTransformer(state?.data?.get(0)?.featured!!.size))
            }

        } catch (ex: Exception) {
            Log.e("setViewPager", ex.message.toString())
        }
    }

    private fun setEditorViewPager(state: UIState.Success<List<VitrinResponse>>?) {
        try {
            if (state == null) {
                pagerVitrinEditor.adapter = HomeEditorRecyclerAdapter(
                    editorList,
                    requireContext()
                )
            } else {
                editorList = state.data[4].shops
                pagerVitrinEditor.adapter = HomeEditorRecyclerAdapter(
                    state.data[4].shops,
                    requireContext()
                )
            }
            pagerVitrinEditor.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        } catch (ex: Exception) {
            Log.e("setEditorViewPager", ex.message.toString())
        }
    }

    private fun alertDialog() {
        try {
            val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
            dialogBuilder.setMessage("Uygulama verilerinde hata oluştu. Uygulamadan çıkış yapılacak")
                .setCancelable(false)
                .setPositiveButton("Tamam") { dialog, id ->
                    dialog.dismiss()
                    System.exit(0)
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Bilgilendirme")
            alert.show()
        } catch (ex: Exception) {
            Log.e("alertDialog", ex.message.toString())
        }
    }

    private val vitrinObserver = Observer<UIState<List<VitrinResponse>>> { state ->
        when (state) {
            is UIState.Loading -> {
                showLoading.value = state.isLoading
            }
            is UIState.Failure -> {

                //show
                alertDialog()

            }
            is UIState.Success -> {
                //delete
                //insert
                checkPermission()
                setViewPager(state)
                setEditorViewPager(state)
                setProductRecyclerview(state)
                setCategoryRecyclerview(state)
                setCollectionRecyclerview(state)
                setNewVitrinRecyclerview(state)

            }
        }
    }
}