plugins {
    java
    alias(libs.plugins.idea.ext)
    alias(libs.plugins.blossom)
    alias(libs.plugins.shadow)
}

dependencies {
    compileOnly(libs.miniplaceholders)
    compileOnly(libs.adventure.api)
    compileOnly(libs.adventure.minimessage)
    implementation(projects.platforminfoExpansionPaper)
    implementation(projects.platforminfoExpansionVelocity)
    implementation(projects.platforminfoExpansionSponge)
    implementation(projects.platforminfoExpansionFabric)
    implementation(projects.platforminfoExpansionCommon)
}

allprojects {
    apply<JavaPlugin>()
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }
}

tasks {
    shadowJar {
        archiveFileName.set("PlatformInfo-Expansion-${project.version}.jar")
        archiveClassifier.set("")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    build {
        dependsOn(shadowJar)
    }
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
            }
        }
    }
}
