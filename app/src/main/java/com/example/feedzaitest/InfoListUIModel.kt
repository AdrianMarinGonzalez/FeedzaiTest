package com.example.presentation.item.models


sealed class ItemListUIModel {
    data object Loading : ItemListUIModel()
    data class Content(val items: List<ItemListItem> = emptyList()) :
        ItemListUIModel()

    data object NotFoundError : ItemListUIModel()
    data class ItemListItem(val id: String, val title: String, val iconURL: String)
}
