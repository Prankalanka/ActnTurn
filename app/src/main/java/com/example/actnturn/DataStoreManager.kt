package com.example.actnturn

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("SETTINGS")

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    val serviceSwitchKey: Key<Boolean> = booleanPreferencesKey("service_switch")
    val serviceSwitch: Flow<Boolean> =
        dataStore.data.map {
            preferences -> val nonNullServiceSwitch = (preferences[serviceSwitchKey])?: false
            nonNullServiceSwitch
        }

    suspend fun <T> saveValue(key: Key<T>, value: T) {
       dataStore.edit { preferences -> preferences[key] = value }
    }
}