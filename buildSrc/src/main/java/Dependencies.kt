import org.gradle.api.JavaVersion

object AppVersionInfo {
    const val versionCode = 1
    const val versionName = "1.0"
}

object ApiVersions {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
}

object jvm {
    const val jvmTarget = "1.8"
    val javaVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val core_ktx = "1.9.0"
    const val appcompat = "1.6.1"
    const val savedStateKtx = "1.2.0"
    const val material = "1.8.0"
    const val constraint = "2.1.4"

    const val testImplJunit = "4.13.2"
    const val androidTestImplJunit = "1.1.5"
    const val androidTestEspresso = "3.5.1"

    const val gson = "2.10.1"

    const val retrofit = "2.9.0"
    const val gsonConvertor = "2.9.0"
    const val okHttp = "4.9.0"

    const val kotlinCoroutines = "1.6.4"
    const val lifecycle = "2.5.1"

    const val glide = "4.14.2"

    const val hilt = "2.44"

    const val room = "2.5.0"

    const val navigationKtx = "2.5.3"

    const val dsPreferences = "1.0.0"

    const val pagination = "3.1.1"
}

object Project {
    const val app = ":app"
    const val presentation = ":presentation"
    const val domain = ":domain"
    const val data = ":data"
    const val remote = ":remote"
    const val local = ":local"
    const val resource = ":resource"
}

object Impl {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val savedStateKtx = "androidx.savedstate:savedstate-ktx:${Versions.savedStateKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLyt = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.testImplJunit}"
}

object AndroidTestImpl {
    const val junit = "androidx.test.ext:junit:${Versions.androidTestImplJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Versions.gsonConvertor}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object Gson {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Coroutines {
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
}

object Lifecycle {
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Hilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
}

object Room {
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomPagination = "androidx.room:room-paging:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
}

object JetpackNavigation {
    const val navFragKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKtx}"
    const val navUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationKtx}"
}

object DatastorePreferences {
    const val dsPreferences = "androidx.datastore:datastore-preferences:${Versions.dsPreferences}"
}

object Pagination {
    const val pagination = "androidx.paging:paging-runtime:${Versions.pagination}"
}

