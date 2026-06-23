package com.ahpalace.rentmanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OfflinePaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: OfflinePayment)

    @Query("SELECT * FROM offline_payment_queue WHERE syncStatus = :status ORDER BY createdAt ASC")
    suspend fun getByStatus(status: String): List<OfflinePayment>

    @Query("UPDATE offline_payment_queue SET syncStatus = :status, retryCount = :retry WHERE localId = :id")
    suspend fun updateStatus(id: String, status: String, retry: Int)
}
