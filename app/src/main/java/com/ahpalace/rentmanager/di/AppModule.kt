package com.ahpalace.rentmanager.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.ahpalace.rentmanager.data.local.AppDatabase
import com.ahpalace.rentmanager.data.local.OfflinePaymentDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File
import androidx.datastore.preferences.core.PreferencesSerializer
import androidx.datastore.preferences.core.emptyPreferences

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.dataStore by preferencesDataStore(name = "time_prefs")

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

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore
}
