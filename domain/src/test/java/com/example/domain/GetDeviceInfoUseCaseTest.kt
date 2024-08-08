package com.example.domain

import com.example.domain.base.Success
import com.example.domain.base.get
import com.example.domain.model.DeviceInfo
import com.example.domain.model.Language
import com.example.domain.model.Timezone
import com.example.domain.repository.DeviceInfoRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetDeviceInfoUseCaseTest {
    @Mock
    lateinit var deviceInfoRepository: DeviceInfoRepository

    lateinit var getDeviceInfoUseCase: GetDeviceInfoUseCase


    @Before
    fun setupCommon() {
        MockitoAnnotations.openMocks(this)
        getDeviceInfoUseCase = GetDeviceInfoUseCase(deviceInfoRepository)
    }


    @Test
    fun `when GetDeviceInfoUseCase executes, data should be retrieved by the DeviceInfoRepository`() {
        val mockTimezone = Timezone("Europe/Lisbon")
        Mockito.`when`(deviceInfoRepository.getTimezone()).thenReturn(mockTimezone)
        val mockLanguage = Language("es-ES")
        Mockito.`when`(deviceInfoRepository.getLanguage()).thenReturn(mockLanguage)

        getDeviceInfoUseCase.execute()

        Mockito.verify(deviceInfoRepository, Mockito.times(1)).getTimezone()
        Mockito.verify(deviceInfoRepository, Mockito.times(1)).getLanguage()
    }

    @Test
    fun `when GetNetworkInfoUseCase retrieves the data, it should return a Success operation`() {
        val mockTimezone = Timezone("Europe/Lisbon")
        val mockLanguage = Language("es-ES")
        Mockito.`when`(deviceInfoRepository.getTimezone()).thenReturn(mockTimezone)
        Mockito.`when`(deviceInfoRepository.getLanguage()).thenReturn(mockLanguage)

        val result = getDeviceInfoUseCase.execute()

        Assert.assertTrue(result is Success)
        Assert.assertTrue(result.get() is DeviceInfo)
        val assertedSuccess = result.get() as DeviceInfo
        Assert.assertEquals(assertedSuccess.timeZone, mockTimezone)
        Assert.assertEquals(assertedSuccess.language, mockLanguage)
    }
}