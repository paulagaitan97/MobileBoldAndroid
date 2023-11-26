apply {
    from("$rootDir/configuracion-base.gradle")
}

dependencies {
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.gsonConverter)
    "implementation"(Gson.gson)
}