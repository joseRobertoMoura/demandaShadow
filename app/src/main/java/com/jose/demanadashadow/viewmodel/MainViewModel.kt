package com.jose.demanadashadow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jose.demanadashadow.data.api.DemandaShadowApiTask
import com.jose.demanadashadow.data.repository.DemandaShadowRepository
import com.jose.demanadashadow.model.DemandaShadowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: DemandaShadowRepository) : ViewModel() {

    private var _dataList = MutableLiveData<List<DemandaShadowModel>>()
    val dataList: LiveData<List<DemandaShadowModel>>
        get() = _dataList

    fun init(){
        getDataFromApi()
    }

    fun getDataFromApi() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                repository.requestDemandaShadowApi(
                    ::onRequestSuccess,
                    ::onRequestError
                )
            }
        }
    }

    fun onRequestError() {
        _dataList.postValue(null)
    }

    fun onRequestSuccess(list: List<DemandaShadowModel>) {
        _dataList.postValue(list)
    }

    class MainViewModelFactory(
        private val repository: DemandaShadowRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }
}