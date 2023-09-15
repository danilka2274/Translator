import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.translator"
    const val compile_sdk = 33
    const val min_sdk = 29
    const val target_sdk = 33
    val java_version = JavaVersion.VERSION_17
    const val jvm_target = "17"
    const val view_binding = true
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
}

object Versions {
    //Design
    const val appcompat = "1.6.1"
    const val material = "1.9.0"
    const val swipeRefreshLayout = "1.1.0"

    //Kotlin
    const val core = "1.10.1"
    const val coroutinesCore = "1.4.3"
    const val coroutinesAndroid = "1.4.3"


    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "5.0.0-alpha.3"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinCore = "3.1.4"
    const val koinAndroid = "3.1.4"

    //Coil
    const val coil = "1.4.0"

    //Room
    const val roomKtx = "2.5.2"
    const val runtime = "2.5.2"
    const val roomCompiler = "2.5.2"

    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.1.5"
    const val espressoCore = "3.5.1"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}