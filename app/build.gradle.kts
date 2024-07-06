plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.cupcake"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cupcake"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    //Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
    //Material
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.material3:material3:1.2.1")
    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.3")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.0-beta04")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    //Compose
    implementation("androidx.compose.ui:ui:1.6.8")
    implementation("androidx.compose.ui:ui-tooling:1.6.8")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")
    implementation("androidx.activity:activity-compose:1.9.0")
}