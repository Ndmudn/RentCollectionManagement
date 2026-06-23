package com.ahpalace.rentmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OfflinePayment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offlinePaymentDao(): OfflinePaymentDao
}
