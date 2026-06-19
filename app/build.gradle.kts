import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun getGitTag(): String {
    return try {
        val process = ProcessBuilder("git", "describe", "--tags", "--exact-match")
            .directory(rootDir)
            .redirectErrorStream(true)
            .start()
        val output = process.inputStream.bufferedReader().readText().trim()
        process.waitFor()
        if (process.exitValue() == 0) output else ""
    } catch (e: Exception) {
        ""
    }
}

fun versionNameFromTag(): String {
    val tag = getGitTag()
    if (tag.isEmpty()) return "0.0.0-dev"
    return tag.removePrefix("v").substringBefore("-")
}

fun versionCodeFromTag(): Int {
    val name = versionNameFromTag()
    if (name == "0.0.0-dev") return 1
    val parts = name.split(".").map { it.toIntOrNull() ?: 0 }
    val major = parts.getOrElse(0) { 0 }
    val minor = parts.getOrElse(1) { 0 }
    val patch = parts.getOrElse(2) { 0 }
    return major * 1_000_000 + minor * 1_000 + patch
}

println("DEBUG: Detected tag = '${getGitTag()}'")
println("DEBUG: versionName = '${versionNameFromTag()}'")
println("DEBUG: versionCode = '${versionCodeFromTag()}'")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.firebase.appdistribution)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.nhdtech.apps.weathermind"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.nhdtech.apps.weathermind"
        minSdk = 24
        targetSdk = 36
        versionCode = versionCodeFromTag()
        versionName = versionNameFromTag()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("weathermind.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotesFile = "release_notes.txt"
                // testers = "tester1@email.com,tester2@email.com"
                // OR use tester groups:
                // groups = "qa-team"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // --- Navigation ---
    implementation(libs.androidx.navigation.compose)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // --- Hilt ---
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // --- Coroutines ---
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // --- Location ---
    implementation(libs.google.play.services.location)

    implementation(platform(libs.firebase.bom))

    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":feature-home:ui"))
    implementation(project(":feature-home:domain"))
    implementation(project(":feature-home:data"))
    implementation(project(":feature-cities:data"))
    implementation(project(":feature-cities:ui"))
    implementation(project(":feature-cities:domain"))
    implementation(project(":feature-settings:ui"))
    implementation(project(":feature-settings:domain"))
    implementation(project(":feature-settings:data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}