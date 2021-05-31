package com.example.androidcase.ui.fragment.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcase.data.repository.DataRepository
import com.example.testappp.data.state.StateLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {


    public val getAllRestaurantApi =
        StateLiveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            dataRepository.getVitrinData()
        }
}