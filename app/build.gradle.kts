plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample"
    compileSdk = ApiVersions.compileSdk

    defaultConfig {
        applicationId = "com.anime_clean_sample"
        minSdk = ApiVersions.minSdk
        targetSdk = ApiVersions.targetSdk
        versionCode = AppVersionInfo.versionCode
        versionName = AppVersionInfo.versionName

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
        sourceCompatibility = Jvm.javaVersion
        targetCompatibility = Jvm.javaVersion
    }

    kotlinOptions {
        jvmTarget = Jvm.jvmTarget
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