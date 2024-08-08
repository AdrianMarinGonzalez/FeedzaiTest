package com.example.data.di

import android.content.Context
import com.example.data.local.ip.IPLocalDatasource
import com.example.data.network.base.HttpClient
import com.example.data.network.base.feedzaiEndpoint
import com.example.data.network.gathering.GatherDataNetworkDatsource
import com.example.data.network.gathering.GatherDataService
import com.example.data.network.ip.IPInfoMapper
import com.example.data.network.ip.IPNetworkDatasource
import com.example.data.network.ip.IPService
import com.example.data.repository.DeviceInfoRepositoryImpl
import com.example.data.repository.GatheringRepositoryImpl
import com.example.data.repository.LocationRepositoryImpl
import com.example.data.repository.NetworkInfoRepositoryImpl
import com.example.domain.repository.DeviceInfoRepository
import com.example.domain.repository.GatheringRepository
import com.example.domain.repository.LocationRepository
import com.example.domain.repository.NetworkInfoRepository
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getModule(applicationContext: Context) = listOf(
    module {
        factory<HttpClient> { HttpClient() }
        factory<OkHttpClient> { provideOkHttpClient(get()) }

        factory<Retrofit>(named("FeedzaiAPI")) { provideFeedzaiAPIRetrofit() }
        factory<Retrofit>(named("IPResolver")) { provideIPResolverAPIRetrofit() }

        single<IPService> { provideIPService(get(named("IPResolver"))) }
        factory<IPNetworkDatasource> { IPNetworkDatasource(get()) }
        factory<IPLocalDatasource> { IPLocalDatasource() }

        single<GatherDataService> { provideGatherDataService(get(named("FeedzaiAPI"))) }
        factory<GatherDataNetworkDatsource> { GatherDataNetworkDatsource(get()) }

        factory<IPInfoMapper> { IPInfoMapper() }

        factory<DeviceInfoRepository> { DeviceInfoRepositoryImpl() }
        factory<LocationRepository> { LocationRepositoryImpl(applicationContext) }
        factory<NetworkInfoRepository> { NetworkInfoRepositoryImpl(applicationContext, get(), get(), get()) }
        factory<GatheringRepository> { GatheringRepositoryImpl(get()) }
    }
)

private fun provideOkHttpClient(client: HttpClient): OkHttpClient =
    client.buildOKHttpClient()

private fun provideFeedzaiAPIRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(feedzaiEndpoint)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()


private fun provideIPResolverAPIRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://api.ipify.org")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

private fun provideIPService(retrofit: Retrofit): IPService =
    retrofit.create(IPService::class.java)

private fun provideGatherDataService(retrofit: Retrofit): GatherDataService =
    retrofit.create(GatherDataService::class.java)