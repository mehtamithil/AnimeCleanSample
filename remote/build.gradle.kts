plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.data.source.remote"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(Project.data))
    implementation(project(Project.resource))

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)

    testImplementation(TestImpl.junit)
    androidTestImplementation(AndroidTestImpl.junit)

    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)

    implementation(Gson.gson)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConvertor)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.httpLoggingInterceptor)

    implementation(Pagination.pagination)
}