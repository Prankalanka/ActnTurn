package com.example.actnturn

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _serviceSwitch = MutableStateFlow(ToggleableInfo(isChecked = false, text = "Service running"))
    val serviceSwitch = _serviceSwitch.asStateFlow()

    fun getServiceSwitch()
    {
        return dataStoreManager.
    }


    fun toggleServiceSwitch() {
        _serviceSwitch.value = _serviceSwitch.value.copy(isChecked = !_serviceSwitch.value.isChecked)
    }
}