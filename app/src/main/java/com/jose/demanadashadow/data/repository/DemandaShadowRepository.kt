package com.jose.demanadashadow.data.repository

import com.jose.demanadashadow.data.api.DemandaShadowApiTask
import com.jose.demanadashadow.model.DemandaShadowModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemandaShadowRepository(private val demandaShadowApiTask: DemandaShadowApiTask) {

    companion object {
        private const val SUCCESS_CODE = 200
    }

    fun requestDemandaShadowApi(
        onSuccess: (List<DemandaShadowModel>) -> Unit,
        onError: () -> Unit
    ) {
            val request = demandaShadowApiTask.demandaShadowApi().getDataDemandaShadowApi()

            request.enqueue(object : Callback<List<DemandaShadowModel>> {

                override fun onResponse(
                    call: Call<List<DemandaShadowModel>>,
                    response: Response<List<DemandaShadowModel>>
                ) {
                    val responseCode = response.code()

                    if (responseCode == SUCCESS_CODE) {
                        val listResponse: List<DemandaShadowModel> = response.body()!!
                        onSuccess.invoke(listResponse)
                    } else {
                        onError()
                    }
                }

                override fun onFailure(call: Call<List<DemandaShadowModel>>, t: Throwable) {
                    onError()
                }
            })
    }

}