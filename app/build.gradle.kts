import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.navigationSafeargs)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.lokesh.emptyandroidproject"
    compileSdk = 36
    flavorDimensions += "type"


    defaultConfig {
        applicationId = "com.lokesh.emptyandroidproject"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "DEBUG_API_BASE_URL", "\"${project.properties["debug_api_base_url"]}\"")
        buildConfigField("String", "RELEASE_API_BASE_URL", "\"${project.properties["release_api_base_url"]}\"")
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
    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            // proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("boolean", "LOG", "false")
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            // proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("boolean", "LOG", "true")
        }
    }
    productFlavors {
        create("staging") {
            // applicationIdSuffix = ".staging"
            val formattedDate = SimpleDateFormat("hh-mm-a").format(Date())
            versionNameSuffix = "-staging-$formattedDate"
            dimension = "type"
            manifestPlaceholders.putAll(mapOf("firebaseLog" to "true"))
        }
        create("prod") {
            dimension = "type"
            manifestPlaceholders.putAll(mapOf("firebaseLog" to "true"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    bundle {
        language {
            enableSplit = false
        }
    }
    lint {
        checkReleaseBuilds = false
        disable += mutableSetOf("ContentDescription", "Autofill", "UseCompoundDrawables", "NotifyDataSetChanged", "RecyclerView", "UnusedAttribute")
    }
    applicationVariants.configureEach {
        val variant = this
        val dateTime = SimpleDateFormat("dd-MM-yyyy").format(Date())
        outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "Kharcha-${variant.versionName}-$dateTime.apk"
                output.outputFileName = outputFileName
            }
    }
}

ktlint {
    android.set(true)
    debug.set(true)
    verbose.set(true)
    ignoreFailures.set(true)
    outputToConsole.set(true)
    disabledRules.set(listOf("import-ordering", "final-newline", "no-wildcard-imports"))
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.performance)
    implementation(libs.firebase.config)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.google.auth)
    // lifecycle
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.common)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.extension)
    // retrofit
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)
    // okhttp
    implementation(libs.okttp)
    implementation(libs.okttp.logging.interceptor)
    // pie-chart
    implementation(libs.mpandroidchart)
    // splash screen
    implementation(libs.androidx.splashscreen)
    // jetpack navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // koin
    implementation(libs.koin)
    //implementation(libs.koin.viewmodel)
    implementation(libs.koin.work)
    // multidex
    implementation(libs.androidx.multidex)
    // lottie
    implementation(libs.lottie)
    // glide
    implementation(libs.glide)
    // circular imageview
    implementation(libs.circular.imageview)
    // coroutines
    implementation(libs.kotlin.coroutines)
    // datastore preferences
    implementation(libs.androidx.datastore)
    // play core for in app review and in-app updates
    implementation(libs.play.app.update)
    implementation(libs.play.app.review)
    // paging
    implementation(libs.androidx.paging)
    // dimensions
    implementation(libs.dimension.ssp)
    implementation(libs.dimension.sdp)
    // biometric
    implementation(libs.androidx.biometric)
    // flexbox
    implementation(libs.flexbox)
    // compose
    implementation(platform(libs.compose))
    implementation(libs.compose.foundation)
    implementation(libs.compose.material.ui)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.runtime.livedata)
    // objectbox
    debugImplementation(libs.objectbox.browser)
    releaseImplementation(libs.objectbox.android)
    // shimmer
    implementation(libs.facebook.shimmer)
    // meta ad sdk
    implementation(libs.facebook.android.sdk)
    // onesignal notification
    implementation(libs.onesignal)
    // billing
    implementation(libs.billing)
    // work-manager
    implementation(libs.work.manager)
}

apply { plugin("io.objectbox") }
