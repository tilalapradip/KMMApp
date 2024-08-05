package com.example.kmmapp.viewmodel

import com.example.kmmapp.data.MobileItem
import com.example.kmmapp.dispatchers.ioDispatcher
import com.example.kmmapp.services.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MobileListViewModel : ViewModel() {
    private val apiService: ApiService = ApiService.create()

    private val _mobileDataState: MutableStateFlow<MobileDataState> =
        MutableStateFlow(MobileDataState.Initial)
    val mobileDataState = _mobileDataState.asStateFlow()

    fun getMobiles() {
        viewModelScope.launch(ioDispatcher) {
            val mobileList = apiService.getMobiles()
            _mobileDataState.value = MobileDataState.Success(mobileList)
        }
    }

    fun getMobilesFake() {
        _mobileDataState.value = MobileDataState.Loading
        viewModelScope.launch(ioDispatcher) {
            delay(2000)
            val mobileList = apiService.getMobilesFake()
            _mobileDataState.value = MobileDataState.Success(mobileList)
        }
    }

}

sealed class MobileDataState() {
    data object Initial : MobileDataState()
    data object Loading : MobileDataState()
    data class Success(val data: List<MobileItem>) : MobileDataState()
    data object Failure : MobileDataState()
}
