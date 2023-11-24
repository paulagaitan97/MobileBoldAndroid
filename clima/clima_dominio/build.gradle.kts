apply {
    from("$rootDir/configuracion-base.gradle")
}

dependencies {
    "implementation"(Coroutines.coroutines)
    "implementation"(DaggerHilt.hiltAndroid)
    "kapt"(DaggerHilt.hiltCompiler)
}