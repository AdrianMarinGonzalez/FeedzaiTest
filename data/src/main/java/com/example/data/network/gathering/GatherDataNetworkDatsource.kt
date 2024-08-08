package com.example.data.network.gathering

import com.example.data.network.base.NetworkDatasource


class IPNetworkDatasource constructor(private val service: GatherDataService) : NetworkDatasource() {
    fun getIP() = executeCall(service.getIP())
}