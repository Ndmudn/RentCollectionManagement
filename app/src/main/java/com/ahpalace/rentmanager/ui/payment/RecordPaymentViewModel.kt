package com.ahpalace.rentmanager.ui.payment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class RecordPaymentUiState(
    val transactionId: String = "",
    val isSubmitEnabled: Boolean = false
)

class RecordPaymentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecordPaymentUiState())
    val uiState: StateFlow<RecordPaymentUiState> = _uiState

    fun updateTransactionId(id: String) {
        _uiState.update { it.copy(transactionId = id, isSubmitEnabled = id.isNotBlank()) }
    }

    fun submitPayment() {
        // Validate mandatory fields at ViewModel level, then enqueue to Room or call Cloud Function
    }
}
