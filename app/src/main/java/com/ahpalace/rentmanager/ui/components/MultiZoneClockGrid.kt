package com.ahpalace.rentmanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun MultiZoneClockGrid(
    viewModel: TimeZonesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val nowInstant by viewModel.nowInstant.collectAsState()
    val zones by viewModel.filteredZones.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val time24 by viewModel.timeFormat24.collectAsState()
    var showSelector by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        // Header
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("World Clocks", style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            IconButton(onClick = { showSelector = true }) { Icon(Icons.Filled.Search, contentDescription = "Add Zone") }
            TextButton(onClick = { viewModel.setTimeFormat24(!time24) }) { Text(if (time24) "24h" else "12h") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxHeight()) {
            items(zones) { zoneId ->
                ClockGridCard(
                    zoneId = zoneId,
                    nowInstant = nowInstant,
                    isFavorite = favorites.contains(zoneId),
                    is24h = time24,
                    onToggleFavorite = { viewModel.toggleFavorite(zoneId) }
                )
            }
        }
    }

    if (showSelector) {
        TimezoneSelectorDialog(onDismiss = { showSelector = false }, onAdd = { zone ->
            viewModel.addSavedZone(zone)
            showSelector = false
        })
    }
}

@Composable
private fun ClockGridCard(
    zoneId: String,
    nowInstant: Instant,
    isFavorite: Boolean,
    is24h: Boolean,
    onToggleFavorite: () -> Unit
) {
    val zone = try { ZoneId.of(zoneId) } catch (_: Exception) { ZoneId.systemDefault() }
    val zdt = remember(nowInstant, zone) { ZonedDateTime.ofInstant(nowInstant, zone) }
    val timePattern = if (is24h) "HH:mm:ss" else "hh:mm:ss a"
    val timeFmt = DateTimeFormatter.ofPattern(timePattern, Locale.getDefault())
    val dateFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(zoneId.substringAfterLast('/'), style = MaterialTheme.typography.titleMedium)
                    Text(zoneId, style = MaterialTheme.typography.bodySmall)
                }
                IconButton(onClick = onToggleFavorite) {
                    if (isFavorite) Icon(Icons.Filled.Star, contentDescription = "fav") else Icon(Icons.Outlined.StarBorder, contentDescription = "not fav")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = zdt.format(timeFmt), style = MaterialTheme.typography.titleLarge)
            Text(text = zdt.format(dateFmt) + " (" + zdt.offset.id + ")", style = MaterialTheme.typography.bodySmall)
        }
    }
}
