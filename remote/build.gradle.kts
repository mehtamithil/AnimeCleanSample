plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.data.source.remote"
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
        sourceCompatibility = jvm.javaVersion
        targetCompatibility = jvm.javaVersion
    }

    kotlinOptions {
        jvmTarget = jvm.jvmTarget
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