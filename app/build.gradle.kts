
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.sentry.android.gradle") version "3.14.0"
}
val API_KEY: String = project.property("API_KEY") as String? ?: "No Api"

android.buildTypes.forEach { type ->
    type.buildConfigField("String", "API_KEY", API_KEY)

}
android {
    compileSdk = ConfiguracionProyecto.compileSdk
    namespace = ConfiguracionProyecto.applicationId
    defaultConfig {
        applicationId = ConfiguracionProyecto.applicationId
        minSdk = ConfiguracionProyecto.minSdk
        targetSdk = ConfiguracionProyecto.targetSdk
        versionCode = ConfiguracionProyecto.versionCode
        versionName = ConfiguracionProyecto.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            getDefaultProguardFile("proguard-android-optimize.txt")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += setOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "**/attach_hotspot_windows.dll",
                "META-INF/licenses/ASM"
            )
        }
    }
}

dependencies {
    implementation(project(Modulos.climaDatos))
    implementation(project(Modulos.climaDominio))
    implementation(project(Modulos.climaPresentacion))
    implementation(project(Modulos.baseUi))
    implementation(project(Modulos.base))
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(Coil.coilCompose)
    implementation(Google.material)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)
    implementation (Monitoreo.sentryAndroid)
    implementation (Monitoreo.sentryHttp)
    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)
    testImplementation(Testing.mockito)
    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
}

sentry {
    org.set("na-20x")
    projectName.set("android-5s")

    // this will upload your source code to Sentry to show it as part of the stack traces
    // disable if you don't want to expose your sources
    includeSourceContext.set(true)
}
