import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

val isCI = System.getenv("CI") == "true"
var appVersionName: String
var appVersionCode: Int

if (isCI) {
    appVersionName = System.getenv("VERSION_NAME") ?: "0.0.0"
    appVersionCode = System.getenv("VERSION_CODE")?.toIntOrNull() ?: 1
} else {
    val versionProps = Properties().apply {
        val file = rootProject.file("version.properties")
        if (file.exists()) load(file.inputStream())
    }
    val major = versionProps["VERSION_MAJOR"]?.toString()?.toIntOrNull() ?: 0
    val minor = versionProps["VERSION_MINOR"]?.toString()?.toIntOrNull() ?: 0
    val patch = versionProps["VERSION_PATCH"]?.toString()?.toIntOrNull() ?: 0
    appVersionName = "$major.$minor.$patch-dev"
    appVersionCode = major * 1_000_000 + minor * 1_000 + patch
}

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
        versionCode = appVersionCode
        versionName = appVersionName
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