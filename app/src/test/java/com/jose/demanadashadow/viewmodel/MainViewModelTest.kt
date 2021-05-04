package com.jose.demanadashadow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jose.demanadashadow.model.DemandaShadowModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataListObserver: Observer<List<DemandaShadowModel>>

    private lateinit var mainViewModel:MainViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Test return api success`(){

        mainViewModel = MainViewModel()
        mainViewModel.dataList.observeForever(dataListObserver)

        mainViewModel.init()

        verify(dataListObserver).onChanged(mainViewModel.dataList.value)

    }
}