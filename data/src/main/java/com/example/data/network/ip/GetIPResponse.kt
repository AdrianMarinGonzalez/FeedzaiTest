package com.example.data.item.network


import com.google.gson.annotations.SerializedName

data class GetItemsResponse(
    @SerializedName("dataCollection") var dataCollection: List<DataCollection> = arrayListOf()
)

data class ImageInfo(
    @SerializedName("imageUrl") var imageUrl: String? = null
)

data class Attributes(
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("imageInfo") var imageInfo: ImageInfo? = ImageInfo()
)

data class Item(
    @SerializedName("id") var id: String? = null,
    @SerializedName("attributes") var attributes: Attributes? = Attributes()
)

data class DataCollection(
    @SerializedName("item") var item: Item? = Item()
)