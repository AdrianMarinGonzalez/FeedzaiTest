package com.example.data.local.ip

import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.model.IPInfo
import java.net.NetworkInterface
import java.util.Collections


class IPLocalDatasource {

    fun getLocalIp(): OperationResult<IPInfo, Error> {
        var result: OperationResult<IPInfo, Error>? = null
        try {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr = addr.hostAddress
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (isIPv4)
                            result = Success(IPInfo(sAddr))
                    }
                }
            }
        } catch (exception: Exception) {
            result = Failure(Error.UncompletedOperation())
        }

        return result ?: Failure(Error.NotFound())
    }
}