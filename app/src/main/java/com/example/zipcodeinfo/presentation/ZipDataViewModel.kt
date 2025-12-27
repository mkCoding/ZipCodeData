package com.example.zipcodeinfo.presentation

import android.net.http.HttpException
import android.net.http.HttpResponseCache
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zipcodeinfo.data.model2.ZipDataModel
import com.example.zipcodeinfo.repository.ZipDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZipDataViewModel @Inject constructor(
    private val repository: ZipDataRepository
): ViewModel() {

    // variable to hold data and use in UI
    private val _zipDataState = MutableStateFlow<ZipDataState>(ZipDataState.Loading)
    val zipDataState: StateFlow<ZipDataState> = _zipDataState

    init {
        loadZipData()
    }

    // load data
    fun loadZipData(zipCode:Int = 48933){
        viewModelScope.launch{
            _zipDataState.value = ZipDataState.Loading
            try {
                val data = repository.getZipDataByZip(zipCode)
                _zipDataState.value = ZipDataState.Success(data)
            }
            catch (e: Exception){
                _zipDataState.value = ZipDataState.Error("${e.message}")

            }
        }
    }

}


sealed class ZipDataState{
    // loading, success, error

    object Loading: ZipDataState()
    data class Success(val data: ZipDataModel): ZipDataState()
    data class Error(val message:String): ZipDataState()
}