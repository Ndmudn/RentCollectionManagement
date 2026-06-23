package com.ahpalace.rentmanager.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimePreferencesRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val KEY_FAVORITES = stringSetPreferencesKey("tz_favorites")
        val KEY_TIME_24H = booleanPreferencesKey("time_format_24h")
        val KEY_SAVED_ZONES = stringSetPreferencesKey("tz_saved_zones")
    }

    val favoritesFlow: Flow<Set<String>> = dataStore.data.map { prefs -> prefs[KEY_FAVORITES] ?: emptySet() }
    val timeFormat24Flow: Flow<Boolean> = dataStore.data.map { prefs -> prefs[KEY_TIME_24H] ?: false }
    val savedZonesFlow: Flow<Set<String>> = dataStore.data.map { prefs -> prefs[KEY_SAVED_ZONES] ?: emptySet() }

    suspend fun toggleFavorite(zoneId: String) {
        dataStore.edit { prefs ->
            val set = prefs[KEY_FAVORITES]?.toMutableSet() ?: mutableSetOf()
            if (set.contains(zoneId)) set.remove(zoneId) else set.add(zoneId)
            prefs[KEY_FAVORITES] = set
        }
    }

    suspend fun setTimeFormat24(value: Boolean) {
        dataStore.edit { prefs -> prefs[KEY_TIME_24H] = value }
    }

    suspend fun addSavedZone(zoneId: String) {
        dataStore.edit { prefs ->
            val set = prefs[KEY_SAVED_ZONES]?.toMutableSet() ?: mutableSetOf()
            set.add(zoneId)
            prefs[KEY_SAVED_ZONES] = set
        }
    }

    suspend fun removeSavedZone(zoneId: String) {
        dataStore.edit { prefs ->
            val set = prefs[KEY_SAVED_ZONES]?.toMutableSet() ?: mutableSetOf()
            set.remove(zoneId)
            prefs[KEY_SAVED_ZONES] = set
        }
    }
}
