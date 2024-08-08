package com.example.data.network.gathering

import com.example.data.network.base.NetworkDatasource
import retrofit2.http.POST


class GatherDataNetworkDatsource constructor(private val service: GatherDataService) : NetworkDatasource() {
    fun sendData(request: GatherDataRequest) = executeCall(service.sendData(request))
}