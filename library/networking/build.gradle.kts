plugins {
    kotlin("jvm")
}

dependencies {
    implementation(platform(libs.di.koin.bom))
    implementation(libs.di.koin.core)

    implementation(platform(libs.networking.retrofit2.bom))
    implementation(libs.networking.retrofit2.retrofit)
    implementation(libs.networking.retrofit2.moshiConverter)

    implementation(libs.networking.moshi)
}