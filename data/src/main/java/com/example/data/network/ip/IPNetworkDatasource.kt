package com.example.data.network.ip

import com.example.data.base.NetworkDatasource
import com.example.data.item.network.IPService


class ItemNetworkDatasource constructor(private val service: IPService): NetworkDatasource() {

    fun getItems() =
        executeCall(
            service.getItems()
        )

}