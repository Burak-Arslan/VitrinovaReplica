package com.example.androidcase.base

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.androidcase.R

open class BaseFragment : Fragment() {
    lateinit private var progressBar: AlertDialog
    val showLoading = MutableLiveData<Boolean>(false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        showLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgress()
            } else {
                hideProgress()
            }
        })

    }

    fun showProgress() {
        if (!(::progressBar.isInitialized)) {
            val adb = activity?.let { AlertDialog.Builder(it) }
            val view = layoutInflater.inflate(R.layout.alert_dialog_progressbar_layout, null)
            var tv = view.findViewById<TextView>(R.id.idTextViewMessage)
            adb!!.setView(view)
            progressBar = adb.create()
            progressBar.setCancelable(false)
        }
        progressBar.show()
    }

    fun hideProgress() {
        if (::progressBar.isInitialized) {
            progressBar.dismiss()
        }
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDetach() {
        super.onDetach()
        if (::progressBar.isInitialized) {
            progressBar.dismiss()
        }
    }
}