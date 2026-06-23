package com.ahpalace.rentmanager.ui.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecordPaymentScreen(viewModel: RecordPaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = uiState.transactionId, onValueChange = { viewModel.updateTransactionId(it) }, label = { Text("Transaction ID *") })
        // other fields omitted for brevity

        Button(onClick = { viewModel.submitPayment() }, enabled = uiState.isSubmitEnabled, modifier = Modifier.padding(top = 16.dp)) {
            Text("Record Payment & Generate Receipt")
        }
    }
}
