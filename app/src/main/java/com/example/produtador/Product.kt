package com.example.produtador

data class Product(
    val id: String,
    val name: String,
    val category: String,
    val price: Float,
    val offer: Float? = null,
    val description: String? = null,
    val images: List<String>
)