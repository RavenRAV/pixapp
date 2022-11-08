package com.example.pixapp.model

data class PixaModel (
    val hits:List<ImageModel>
        )

data class ImageModel (
    val largeImageURL: String
    )
