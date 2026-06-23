package com.ahpalace.rentmanager.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahpalace.rentmanager.data.preferences.TimePreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class TimeZonesViewModel @Inject constructor(private val prefs: TimePreferencesRepository) : ViewModel() {

    private val _nowInstant = MutableStateFlow(Instant.now())
    val nowInstant: StateFlow<Instant> = _nowInstant.asStateFlow()

    // All available zones sorted; put common zones first
    private val allZoneIds: List<String> = listOf(
        "Asia/Kolkata",
        "UTC",
        "America/New_York",
        "Europe/London",
        "Asia/Tokyo",
        "Australia/Sydney"
    ) + ZoneId.getAvailableZoneIds().sorted().filter { it !in listOf("Asia/Kolkata","UTC","America/New_York","Europe/London","Asia/Tokyo","Australia/Sydney") }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val favorites: StateFlow<Set<String>> = prefs.favoritesFlow.stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())
    val timeFormat24: StateFlow<Boolean> = prefs.timeFormat24Flow.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val savedZones: StateFlow<List<String>> = prefs.savedZonesFlow.map { it.toList() }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val filteredZones: StateFlow<List<String>> = combine(searchQuery, savedZones) { q, saved ->
        val base = if (saved.isNotEmpty()) saved else allZoneIds.take(50)
        if (q.isBlank()) base else base.filter { it.contains(q, ignoreCase = true) || it.substringAfterLast('/').contains(q, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, allZoneIds.take(50))

    init {
        viewModelScope.launch {
            while (isActive) {
                _nowInstant.value = Instant.now()
                val sleep = 1000L - (System.currentTimeMillis() % 1000L)
                delay(if (sleep <= 0) 1000L else sleep)
            }
        }
    }

    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(zoneId: String) {
        viewModelScope.launch { prefs.toggleFavorite(zoneId) }
    }

    fun setTimeFormat24(value: Boolean) {
        viewModelScope.launch { prefs.setTimeFormat24(value) }
    }

    fun addSavedZone(zoneId: String) {
        viewModelScope.launch { prefs.addSavedZone(zoneId) }
    }

    fun removeSavedZone(zoneId: String) {
        viewModelScope.launch { prefs.removeSavedZone(zoneId) }
    }
}
