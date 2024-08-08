package com.example.feedzaitest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetDeviceInfoUseCase
import com.example.domain.GetGeolocationInfoUseCase
import com.example.domain.GetNetworkInfoUseCase
import com.example.domain.SendDataUseCase
import com.example.domain.base.get
import com.example.domain.base.mapFailure
import com.example.domain.model.DeviceInfo
import com.example.domain.model.Location
import com.example.domain.model.NetworkInfo
import com.example.domain.model.SensitiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class InfoListViewModel constructor(
    private val getNetworkInfoUseCase: GetNetworkInfoUseCase,
    private val getDeviceInfoUseCase: GetDeviceInfoUseCase,
    private val getGeolocationInfoUseCase: GetGeolocationInfoUseCase,
    private val sendDataUseCase: SendDataUseCase,
    private val itemListUIModelMapper: InfoListLayoutModelMapper
) : ViewModel() {

    private val _items = MutableStateFlow<InfoListUIModel>(InfoListUIModel.Loading)
    val items: StateFlow<InfoListUIModel> = _items.asStateFlow()

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val networkInfo = getNetworkInfoUseCase.execute().mapFailure { NetworkInfo() }.get()
            val deviceInfo = getDeviceInfoUseCase.execute().mapFailure { DeviceInfo() }.get()
            val location = getGeolocationInfoUseCase.execute().mapFailure { Location() }.get()

            _items.value = itemListUIModelMapper.map(deviceInfo, networkInfo, location)

            sendDataUseCase.sendData(SensitiveData(deviceInfo, networkInfo, location))
        }
    }
}