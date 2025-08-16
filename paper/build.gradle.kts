plugins {
    java
}

dependencies {
    compileOnly(libs.papermc)
    compileOnly(libs.miniplaceholders)
    implementation(projects.platforminfoExpansionCommon)
}