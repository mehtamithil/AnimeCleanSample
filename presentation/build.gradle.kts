plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.anime_clean_sample.presentation"
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

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(Project.domain))
    implementation(project(Project.resource))

    implementation(Impl.core_ktx)
    implementation(Impl.appCompat)
    implementation(Impl.savedStateKtx)
    implementation(Impl.material)
    implementation(Impl.constraintLyt)

    testImplementation(TestImpl.junit)
    androidTestImplementation(AndroidTestImpl.junit)
    androidTestImplementation(AndroidTestImpl.espresso)

    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)

    implementation(Lifecycle.lifecycleViewModel)
    implementation(Lifecycle.lifeCycleRuntime)

    implementation(JetpackNavigation.navFragKtx)
    implementation(JetpackNavigation.navUIKtx)

    implementation(Glide.glide)
    annotationProcessor(Glide.glideCompiler)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltCompiler)

    implementation(Pagination.pagination)
}