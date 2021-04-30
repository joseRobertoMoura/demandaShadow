package com.jose.demanadashadow.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DemandaShadowApiTask {
    companion object {
        const val BASE_URL = "https://608bf6279f42b20017c3d34c.mockapi.io/"
    }

    private fun demandaShadowProvider(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun demandaShadowApi():DemandaShadowApi = demandaShadowProvider().create(DemandaShadowApi::class.java)
}