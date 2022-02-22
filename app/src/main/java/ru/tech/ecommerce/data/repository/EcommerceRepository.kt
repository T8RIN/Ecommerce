package ru.tech.ecommerce.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import ru.tech.ecommerce.data.remote.EcommerceApi
import ru.tech.ecommerce.data.remote.response.Home
import ru.tech.ecommerce.utils.Resource
import javax.inject.Inject

@ActivityScoped
class EcommerceRepository @Inject constructor(
    private val api: EcommerceApi
) {

    suspend fun getHome(): Resource<Home> {
        val response = try {
            api.getHome()
        } catch (e: Exception) {
            return Resource.Error(message = e.message.toString())
        }
        return Resource.Success(response)
    }

}