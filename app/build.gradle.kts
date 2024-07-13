plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.cupcake"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cupcake"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    android {
        buildFeatures {
            compose = true
        }
    }
}

dependencies {
    implementation(libs.androidx.compose.material3.android)
    val nav_version = "2.4.1"
    val lifecycle_version = "2.4.1"
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("com.google.android.material:material:1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.0")

    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.foundation:foundation:1.6.8")
    implementation("androidx.compose.foundation:foundation-layout-android:1.6.8")
    implementation("androidx.compose.material:material:1.6.8")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.6.8")
}