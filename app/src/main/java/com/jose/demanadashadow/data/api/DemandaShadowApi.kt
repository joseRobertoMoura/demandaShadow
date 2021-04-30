package com.jose.demanadashadow.data.api

import com.jose.demanadashadow.model.DemandaShadowModel
import retrofit2.Call
import retrofit2.http.GET

interface DemandaShadowApi {

    @GET("demandaShadow")
    fun getDataDemandaShadowApi():Call<List<DemandaShadowModel>>

}