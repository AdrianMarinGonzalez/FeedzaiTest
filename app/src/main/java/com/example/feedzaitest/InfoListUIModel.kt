package com.example.feedzaitest


sealed class InfoListUIModel {
    data object Loading : InfoListUIModel()
    data class Content(val items: List<InfoListItemModel> = emptyList()) :
        InfoListUIModel()

    data object NotFoundError : InfoListUIModel()
    data class InfoListItemModel(val key: String, val value: String)
}
