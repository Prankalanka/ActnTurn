package com.example.actnturn

import android.content.Context
import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("SETTINGS")

private val Keys = object {
    val SERVICE_SWITCH = booleanPreferencesKey("service_switch")
}

class DataStoreManager @Inject constructor(context: Context) {
    private val dataStore = context.dataStore

    // These are vals which means they can't be set, but the emit function does probably set them
    val serviceSwitch: Flow<Boolean> =
        dataStore.data.map { preferences -> preferences[Keys.SERVICE_SWITCH] == true }

    suspend fun <T> getValue(key: Key<T>, value: T)
    {
        return dataStore.data.map()[key]-
    }

    suspend fun saveValue(key: Key<Boolean>, value: Boolean)
    {
       dataStore.edit { preferences -> preferences[key] = value }
    }
}
