plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.anime_clean_sample"
        minSdk = 26
        targetSdk = 33
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

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Project.presentation))
    implementation(project(Project.domain))
    implementation(project(Project.data))
    implementation(project(Project.local))
    implementation(project(Project.remote))
    implementation(project(Project.resource))

    implementation(Impl.core_ktx)
    implementation(Impl.appCompat)
    implementation(Impl.material)

    testImplementation(TestImpl.junit)

    androidTestImplementation(AndroidTestImpl.junit)
    androidTestImplementation(AndroidTestImpl.espresso)

    implementation(JetpackNavigation.navFragKtx)
    implementation(JetpackNavigation.navUIKtx)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)

    implementation(DatastorePreferences.dsPreferences)
}