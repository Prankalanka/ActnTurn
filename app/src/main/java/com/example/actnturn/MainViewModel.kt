package com.example.actnturn

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) : ViewModel() {

    val serviceSwitch = dataStoreManager.serviceSwitch

    fun saveServiceSwitch(value: Boolean) {
        viewModelScope.launch {
            Log.d("GEN", "dataStore change:  $value")
            dataStoreManager.saveValue(dataStoreManager.serviceSwitchKey, value)
        }
    }
}