package org.d3if3068.assesment2.daundiary.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings_preference"
)

class SettingsDataStore(private val context: Context) {

    companion object {
        private val IS_MORNING = booleanPreferencesKey("is_morning")
    }

    val layoutFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_MORNING] ?: true
    }

    suspend fun saveLayout(isMorning: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_MORNING] = isMorning
        }
    }
}