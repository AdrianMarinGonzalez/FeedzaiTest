package com.example.feedzaitest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetDeviceInfoUseCase
import com.example.domain.GetGeolocationInfoUseCase
import com.example.domain.GetNetworkInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class InfoListViewModel constructor(
    private val getNetworkInfoUseCase: GetNetworkInfoUseCase,
    private val getDeviceInfoUseCase: GetDeviceInfoUseCase,
    private val getGeolocationInfoUseCase: GetGeolocationInfoUseCase,
    private val itemListUIModelMapper: InfoListLayoutModelMapper
) : ViewModel() {

    private val _items = MutableStateFlow<InfoListUIModel>(InfoListUIModel.Loading)
    val items: StateFlow<InfoListUIModel> = _items.asStateFlow()

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getNetworkInfoUseCase.getNetworkInfo()
        }
    }
}