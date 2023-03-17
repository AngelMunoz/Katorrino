plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

group = "me.tunaxor.apps"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("com.arkivanov.decompose:decompose:1.0.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:1.0.0")
}

android {
    compileSdkVersion(33)
    defaultConfig {
        applicationId = "com.example.android"
        minSdkVersion(24)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
