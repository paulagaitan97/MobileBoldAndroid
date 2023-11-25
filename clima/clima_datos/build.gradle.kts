apply {
    from("$rootDir/configuracion-base.gradle")
}

dependencies {
    "implementation"(project(Modulos.climaDominio))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.gsonConverter)
    "implementation"(Gson.gson)
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}