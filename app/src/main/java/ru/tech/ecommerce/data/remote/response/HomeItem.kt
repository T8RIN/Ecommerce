package ru.tech.ecommerce.data.remote.response

data class HomeItem(
    val _id: String,
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)