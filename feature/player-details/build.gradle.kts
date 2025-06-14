plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "cz.home.nbaplayers.feature.playerdetails"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
    }

    compileOptions.sourceCompatibility = JavaVersion.VERSION_21
    compileOptions.targetCompatibility = JavaVersion.VERSION_21

    buildFeatures {
        compose=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion=libs.compose.material3.get().version
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.androidx.material3)

    implementation(project(":library:data"))
    implementation(project(":library:networking"))
}