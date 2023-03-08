plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.data.source.local"
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
    implementation(project(Project.data))
    implementation(project(Project.resource))

    testImplementation(TestImpl.junit)
    androidTestImplementation(AndroidTestImpl.junit)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)

    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)

    implementation(DatastorePreferences.dsPreferences)

    implementation(Room.room)
    implementation(Room.roomPagination)
    annotationProcessor(Room.roomCompiler)
    kapt(Room.roomCompiler)

    implementation(Pagination.pagination)
}