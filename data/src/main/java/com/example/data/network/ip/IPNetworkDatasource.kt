package com.example.data.network.ip

import com.example.data.network.base.NetworkDatasource


class IPNetworkDatasource constructor(private val service: IPService) : NetworkDatasource() {
    fun getIP() = executeCall(service.getIP())
}