package com.example.domain

import com.example.domain.base.Success
import com.example.domain.base.get
import com.example.domain.model.Location
import com.example.domain.repository.LocationRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetGeolocationInfoUseCaseTest {
    @Mock
    lateinit var locationRepository: LocationRepository

    lateinit var getGeolocationInfoUseCase: GetGeolocationInfoUseCase


    @Before
    fun setupCommon() {
        MockitoAnnotations.openMocks(this)
        getGeolocationInfoUseCase = GetGeolocationInfoUseCase(locationRepository)
    }


    @Test
    fun `when GetGeolocationInfoUseCase executes, location should be retrieved by the LocationRepository`() {
        val mockLocation = Location(0.0, 0.0)
        Mockito.`when`(locationRepository.getUserLocation()).thenReturn(Success(mockLocation))

        getGeolocationInfoUseCase.execute()

        Mockito.verify(locationRepository, Mockito.times(1)).getUserLocation()
    }

    @Test
    fun `when GetGeolocationInfoUseCase retrieves the data, it should return a Success operation`() {
        val mockLocation = Location(0.0, 0.0)
        Mockito.`when`(locationRepository.getUserLocation()).thenReturn(Success(mockLocation))

        val result = getGeolocationInfoUseCase.execute()

        Assert.assertTrue(result is Success)
        Assert.assertTrue(result.get() is Location)
        val assertedSuccess = result.get() as Location
        Assert.assertEquals(assertedSuccess.latitude, mockLocation.latitude, 0.0)
        Assert.assertEquals(assertedSuccess.longitude, mockLocation.longitude, 0.0)
    }
}