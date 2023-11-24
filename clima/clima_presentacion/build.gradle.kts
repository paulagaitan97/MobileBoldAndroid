apply {
    from("$rootDir/composeui-base.gradle")
}

dependencies {
    "implementation"(Coil.coilCompose)

    "implementation"(project(Modulos.baseUi))
    "implementation"(project(Modulos.climaDatos))
    "implementation"(project(Modulos.climaDominio))
}