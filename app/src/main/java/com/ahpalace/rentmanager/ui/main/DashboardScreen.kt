package com.ahpalace.rentmanager.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahpalace.rentmanager.ui.components.MultiZoneClockGrid

@Composable
fun DashboardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        MultiZoneClockGrid(modifier = Modifier.weight(1f))
        // rest of your dashboard...
    }
}
