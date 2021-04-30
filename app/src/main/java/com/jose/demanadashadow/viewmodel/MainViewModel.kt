package com.jose.demanadashadow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jose.demanadashadow.data.api.DemandaShadowApiTask
import com.jose.demanadashadow.data.repository.DemandaShadowRepository
import com.jose.demanadashadow.model.DemandaShadowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val demandaShadowApiTask = DemandaShadowApiTask()
    private val demandaShadowRepository = DemandaShadowRepository(demandaShadowApiTask)

    private var _dataList = MutableLiveData<List<DemandaShadowModel>>()
    val dataList: LiveData<List<DemandaShadowModel>>
        get() = _dataList

    fun init(){
        getDataFromApi()
    }

    private fun getDataFromApi() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                demandaShadowRepository.requestDemandaShadowApi(
                    ::onRequestSuccess,
                    ::onRequestError
                )
            }
        }
    }

    private fun onRequestError(code: Int?, message: String?) {
        _dataList.postValue(null)
    }

    private fun onRequestSuccess(list: List<DemandaShadowModel>) {
        _dataList.postValue(list)
    }
}