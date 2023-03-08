plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.resource"
    compileSdk = ApiVersions.compileSdk

    defaultConfig {
        minSdk = ApiVersions.minSdk
        targetSdk = ApiVersions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(Impl.core_ktx)
    implementation(Impl.appCompat)
    implementation(Impl.material)

    testImplementation(TestImpl.junit)

    androidTestImplementation(AndroidTestImpl.junit)
    androidTestImplementation(AndroidTestImpl.espresso)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)
}