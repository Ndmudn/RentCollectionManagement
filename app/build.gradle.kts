plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ahpalace.rentmanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ahpalace.rentmanager"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.2"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packagingOptions {
        resources.excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

val composeBom = platform("androidx.compose:compose-bom:2024.01.00")
val firebaseBom = "com.google.firebase:firebase-bom:33.3.0"

dependencies {
    implementation(composeBom)

    // AndroidX / Compose
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")

    // Material 3
    implementation("androidx.compose.material3:material3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")

    // Firebase (BoM-managed)
    implementation(platform(firebaseBom))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-functions-ktx")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.1")

    // DataStore (Proto)
    implementation("androidx.datastore:datastore:1.1.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Firebase Crashlytics
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
