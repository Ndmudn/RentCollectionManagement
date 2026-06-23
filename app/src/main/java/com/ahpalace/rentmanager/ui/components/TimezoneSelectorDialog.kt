package com.ahpalace.rentmanager.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TimezoneSelectorDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val allZones = remember { java.time.ZoneId.getAvailableZoneIds().sorted() }
    val filtered = remember(query) { if (query.isBlank()) allZones.take(200) else allZones.filter { it.contains(query, ignoreCase = true) || it.substringAfterLast('/').contains(query, ignoreCase = true) } }

    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            OutlinedTextField(value = query, onValueChange = { query = it }, label = { Text("Search timezones") })

            LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                items(filtered.size) { idx ->
                    val zone = filtered[idx]
                    TextButton(onClick = { onAdd(zone) }) {
                        Text(zone)
                    }
                }
            }

            Button(onClick = onDismiss, modifier = Modifier.padding(top = 8.dp)) { Text("Close") }
        }
    }
}
