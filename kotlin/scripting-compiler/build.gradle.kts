import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-scripting-compiler:1.4.0")
    compileOnly("org.jetbrains.intellij.deps:asm-all:8.0.1")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlin-scripting-compiler-1.4.0.jar")
            exportPackage("org.jetbrains.kotlin.scripting.*", "org.jetbrains.org.objectweb.asm.*")
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
                    "kotlinx.coroutines.*",
                    "net.rubygrapefruit.platform",
                    "org.jetbrains.kotlin.js.*",
                    "org.jline.*",
                    "com.intellij.*" // TODO !!!!!!!!
            )
        }
    }
}
