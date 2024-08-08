package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.base.HttpClient
import com.example.data.base.MondlyDB
import com.example.data.base.RetrofitInstance
import com.example.data.item.ItemMapper
import com.example.data.item.ItemRepositoryImpl
import com.example.data.item.db.ItemDAO
import com.example.data.item.network.ItemNetworkDatasource
import com.example.data.item.network.ItemService
import com.example.domain.repository.ItemRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

fun getModule(applicationContext: Context) = listOf(
    module {
        factory<HttpClient> { HttpClient() }
        factory<OkHttpClient> { provideOkHttpClient(get()) }
        factory<Retrofit> { RetrofitInstance(get()).get() }
        single<ItemService> { provideItemService(get()) }
        factory<ItemNetworkDatasource> { ItemNetworkDatasource(get()) }
        single {
            Room.databaseBuilder(
                applicationContext,
                MondlyDB::class.java,
                "MondlyDB"
            ).build()
        }
        single<ItemDAO> {
            val database = get<MondlyDB>()
            database.itemDAO()
        }
        factory<ItemMapper> { ItemMapper() }
        factory<ItemRepository> { ItemRepositoryImpl(get(), get(), get()) }
    }
)


private fun provideOkHttpClient(client: HttpClient): OkHttpClient =
    client.buildOKHttpClient()

private fun provideItemService(retrofit: Retrofit): ItemService =
    retrofit.create(ItemService::class.java)