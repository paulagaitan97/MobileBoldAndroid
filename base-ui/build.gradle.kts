apply {
    from("$rootDir/composeui-base.gradle")
}

dependencies {
    "implementation"(Compose.constraintLayoutCompose)
    "implementation"(Coil.coilCompose)
}