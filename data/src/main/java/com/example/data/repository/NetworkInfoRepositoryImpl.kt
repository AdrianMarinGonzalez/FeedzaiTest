package com.example.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.webkit.WebSettings
import com.example.data.local.ip.IPLocalDatasource
import com.example.data.network.ip.IPInfoMapper
import com.example.data.network.ip.IPNetworkDatasource
import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.base.map
import com.example.domain.base.mapFailure
import com.example.domain.model.IPInfo
import com.example.domain.repository.NetworkInfoRepository


class NetworkInfoRepositoryImpl constructor(
    private val context: Context,
    private val ipNetworkDatasource: IPNetworkDatasource,
    private val ipLocalDatasource: IPLocalDatasource,
    private val mapper: IPInfoMapper
) : NetworkInfoRepository {

    override fun getPublicIP(): OperationResult<IPInfo, Error> {
        return ipNetworkDatasource.getIP()
            .map { response ->
                mapper.mapPublicIP(response)
            }
            .mapFailure {
                Error.UncompletedOperation()
            }
    }

    override fun getLocalIP(): OperationResult<IPInfo, Error> = ipLocalDatasource.getLocalIp()

    override fun getDefaultUserAgent(): String {
        return WebSettings.getDefaultUserAgent(context);
    }

    override fun checkVPN(): OperationResult<Boolean, Error> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork;
        val caps = connectivityManager.getNetworkCapabilities(activeNetwork);
        return caps?.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            ?.let { Success(it) }
            ?: Failure(Error.NotFound())
    }
}