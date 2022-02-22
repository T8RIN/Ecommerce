package ru.tech.ecommerce.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import ru.tech.ecommerce.data.remote.response.Home

interface EcommerceApi {

    @Headers("x-apikey: 61ddae2e95cb716ea5ee48e4")
    @GET("home")
    suspend fun getHome(): Home


}