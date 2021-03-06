import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-scripting-common:1.4.0")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlin-scripting-common-1.4.0.jar")
            exportPackage("kotlin.script.*")
            excludePackage(
                    "org.sonatype.aether.*",
                    "com.jcabi.aether",
                    "sun.misc",
                    "sun.nio.ch",
                    "com.sun.*",
                    "org.jetbrains.ide",
                    "org.jetbrains.annotations",
                    "org.jetbrains.kotlin.com.google.errorprone.*",
                    "org.checkerframework.*",
                    // specific
                    "net.rubygrapefruit.platform"
            )
        }
    }
}
