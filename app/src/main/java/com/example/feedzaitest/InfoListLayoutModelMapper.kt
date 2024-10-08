package com.example.feedzaitest

import com.example.domain.model.DeviceInfo
import com.example.domain.model.Language
import com.example.domain.model.Location
import com.example.domain.model.NetworkInfo
import com.example.domain.model.SensitiveData

class InfoListLayoutModelMapper {
    fun map(deviceInfo: DeviceInfo, networkInfo: NetworkInfo, location: Location) =
        InfoListUIModel.Content(
            listOf(
                InfoListUIModel.InfoListItemModel("Local IP", networkInfo.localIP.ip),
                InfoListUIModel.InfoListItemModel("Public IP", networkInfo.publicIP.ip),
                InfoListUIModel.InfoListItemModel("User Agent", networkInfo.userAgent),
                InfoListUIModel.InfoListItemModel("VPN Active", networkInfo.activeVPN.toString()),
                InfoListUIModel.InfoListItemModel("Language", deviceInfo.language.iso),
                InfoListUIModel.InfoListItemModel("Timezone", deviceInfo.timeZone.key),
                InfoListUIModel.InfoListItemModel(
                    "Location",
                    "[${location.latitude} , ${location.longitude}]"
                ),
            )
        )
}
