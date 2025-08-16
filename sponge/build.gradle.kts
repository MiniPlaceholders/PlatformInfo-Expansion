plugins {
    java
}

dependencies {
    compileOnly(libs.miniplaceholders)
    compileOnly(libs.sponge)
    implementation(projects.platforminfoExpansionCommon)
}