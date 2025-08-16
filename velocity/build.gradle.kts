plugins {
    java
}

dependencies {
    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
    compileOnly(libs.miniplaceholders)
    implementation(projects.platforminfoExpansionCommon)
}
