plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.data"
    compileSdk = ApiVersions.compileSdk

    defaultConfig {
        minSdk = ApiVersions.minSdk
        targetSdk = ApiVersions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
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
    api(project(Project.domain))
    implementation(project(Project.resource))

    testImplementation(TestImpl.junit)
    androidTestImplementation(AndroidTestImpl.junit)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)

    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)

    implementation(DatastorePreferences.dsPreferences)

    implementation(Pagination.pagination)
}