package com.example.data.network.ip

import com.example.domain.model.IPInfo


class IPInfoMapper {
    fun mapPublicIP(response: GetIPResponse) = IPInfo(response.ip)
}