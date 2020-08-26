import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.4.0")
    compileOnly("org.jetbrains.intellij.deps:trove4j:1.0.20181211")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlin-compiler-embeddable-1.4.0.jar")
            privatePackage("javaslang.*", "gnu.trove.*", "kotlin.*")
            exportPackage("org.jetbrains.kotlin.*")
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
                    "org.jetbrains.kotlin.com.intellij.ui.mac.foundation",
                    "org.jetbrains.kotlin.com.intellij.util.ui",
                    "org.jetbrains.kotlin.org.apache.commons.collections.map" // TODO ?
            )
        }
    }
}
