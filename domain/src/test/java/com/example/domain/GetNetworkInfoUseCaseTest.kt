package com.example.domain

import com.example.domain.base.Success
import com.example.domain.base.get
import com.example.domain.model.IPInfo
import com.example.domain.model.NetworkInfo
import com.example.domain.repository.NetworkInfoRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetNetworkInfoUseCaseTest {

    @Mock
    lateinit var networkInfoRepository: NetworkInfoRepository

    lateinit var getNetworkInfoUseCase: GetNetworkInfoUseCase


    @Before
    fun setupCommon() {
        MockitoAnnotations.openMocks(this)
        getNetworkInfoUseCase = GetNetworkInfoUseCase(networkInfoRepository)
    }


    @Test
    fun `when GetNetworkInfoUseCase executes, data should be retrieved by the NetworkInfoRepository`() {
        val mockPublicIP = IPInfo("11.111.111.11")
        Mockito.`when`(networkInfoRepository.getPublicIP()).thenReturn(Success(mockPublicIP))
        val mockLocalIP = IPInfo("192.168.1.2")
        Mockito.`when`(networkInfoRepository.getLocalIP()).thenReturn(Success(mockLocalIP))
        val mockUserAgent = "Mock/1.1.1"
        Mockito.`when`(networkInfoRepository.getDefaultUserAgent()).thenReturn(mockUserAgent)
        val mockVPNStatus = true
        Mockito.`when`(networkInfoRepository.checkVPN()).thenReturn(Success(mockVPNStatus))

        getNetworkInfoUseCase.execute()

        Mockito.verify(networkInfoRepository, Mockito.times(1)).getPublicIP()
        Mockito.verify(networkInfoRepository, Mockito.times(1)).getLocalIP()
        Mockito.verify(networkInfoRepository, Mockito.times(1)).getDefaultUserAgent()
        Mockito.verify(networkInfoRepository, Mockito.times(1)).checkVPN()
    }


    @Test
    fun `when GetNetworkInfoUseCase retrieves the data, it should return a Success operation`() {
        val mockPublicIP = IPInfo("11.111.111.11")
        val mockLocalIP = IPInfo("192.168.1.2")
        val mockUserAgent = "Mock/1.1.1"
        val mockVPNStatus = true
        Mockito.`when`(networkInfoRepository.getPublicIP()).thenReturn(Success(mockPublicIP))
        Mockito.`when`(networkInfoRepository.getLocalIP()).thenReturn(Success(mockLocalIP))
        Mockito.`when`(networkInfoRepository.getDefaultUserAgent()).thenReturn(mockUserAgent)
        Mockito.`when`(networkInfoRepository.checkVPN()).thenReturn(Success(mockVPNStatus))

        val result = getNetworkInfoUseCase.execute()

        Assert.assertTrue(result is Success)
        Assert.assertTrue(result.get() is NetworkInfo)
        val assertedSuccess = result.get() as NetworkInfo
        Assert.assertEquals(assertedSuccess.publicIP, mockPublicIP)
        Assert.assertEquals(assertedSuccess.localIP, mockLocalIP)
        Assert.assertEquals(assertedSuccess.userAgent, mockUserAgent)
        Assert.assertEquals(assertedSuccess.activeVPN, mockVPNStatus)
    }
}