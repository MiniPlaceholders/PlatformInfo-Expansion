plugins {
    id("fabric-loom")
}

dependencies {
    compileOnly(libs.miniplaceholders)
    compileOnly(projects.platforminfoExpansionCommon)
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modCompileOnly(libs.fabric.loader)
    modCompileOnly(libs.fabric.api)
    modCompileOnly(libs.adventure.platform.fabric)
}

//tasks {
//    remapJar {
//        //inputFile.set(shadowJar.get().archiveFile)
//        archiveFileName.set("Expansion-Fabric-${project.version}.jar")
////        doLast {
////            copy {
////                from(archiveFile)
////                into("${rootProject.projectDir}/build")
////            }
////        }
//    }
////    shadowJar {
////        configurations = listOf(shade)
////    }
//}
