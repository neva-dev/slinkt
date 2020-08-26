import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.4.0")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlin-stdlib-1.4.0.jar")
            exportPackage("kotlin.*")
            excludePackage("kotlin.internal.*")
            bndTool {
                bnd(mapOf(
                        "-fixupmessages.classes" to "\"Classes found in the wrong directory*\";is:=warning"
                ))
            }
        }
    }
}
