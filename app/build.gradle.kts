plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "br.com.devandroid.registrodeeventosroom"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "br.com.devandroid.registrodeeventosroom"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //room
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:${room_version}")
    kapt("androidx.room:room-compiler:$room_version")


    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")


    val lifecycle_version = "2.8.0" // Use a versão mais recente

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    //ktx
    implementation("androidx.fragment:fragment-ktx:1.8.9")

    // Test helpers
    testImplementation("androidx.room:room-testing:${room_version}")

    // Truth
    testImplementation("com.google.truth:truth:1.4.2")

    //mockito
    testImplementation("org.mockito:mockito-core:5.22.0")

    //roboeletric
    testImplementation("org.robolectric:robolectric:4.16")

}