apply {
    from("$rootDir/composeui-base.gradle")
}


dependencies {
    "implementation"(Coil.coilCompose)
    "implementation"(Compose.constraintLayoutCompose)
    "implementation"(project(Modulos.baseUi))
    "implementation"(project(Modulos.climaDatos))
    "implementation"(project(Modulos.climaDominio))
}