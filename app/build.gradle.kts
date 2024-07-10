plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.newsreaderapp"
    compileSdk = 34

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.newsreaderapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation (libs.androidx.core.ktx.v1101)
    implementation (libs.appcompat.v161)
    implementation (libs.material.v190)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation (libs.moshi)
    implementation (libs.moshi.kotlin)
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.activity.v181)
    implementation (libs.androidx.swiperefreshlayout.v110)
    implementation (libs.picasso.v28)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)
}