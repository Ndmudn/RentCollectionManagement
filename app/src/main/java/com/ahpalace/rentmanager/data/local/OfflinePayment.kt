package com.ahpalace.rentmanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offline_payment_queue")
data class OfflinePayment(
    @PrimaryKey val localId: String,
    val idempotencyKey: String,
    val payloadJson: String,
    val createdAt: Long,
    val syncStatus: String,
    val retryCount: Int
)
