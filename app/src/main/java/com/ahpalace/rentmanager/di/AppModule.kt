package com.ahpalace.rentmanager.di

import android.content.Context
import androidx.room.Room
import com.ahpalace.rentmanager.data.local.AppDatabase
import com.ahpalace.rentmanager.data.local.OfflinePaymentDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ahpalace-db").build()
    }

    @Provides
    fun provideOfflinePaymentDao(db: AppDatabase): OfflinePaymentDao = db.offlinePaymentDao()
}
