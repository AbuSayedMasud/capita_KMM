plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

android {
    namespace = "com.leads.capitabull.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.leads.capitabull.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

//    val composeBom = platform("androidx.compose:compose-bom:2022.10.00")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation ("androidx.compose.foundation:foundation:1.5.4")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation ("com.google.accompanist:accompanist-pager:0.30.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.0")

    implementation ("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    //noinspection GradleDependency
    implementation ("androidx.navigation:navigation-compose:2.7.4")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.0"))

    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation ("io.coil-kt:coil-compose:2.4.0")
    implementation ("io.coil-kt:coil:2.4.0")

    //biometric finger print
    implementation("androidx.biometric:biometric:1.2.0-alpha05")

    //Stepper implementation
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("com.airbnb.android:lottie-compose:6.1.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
}