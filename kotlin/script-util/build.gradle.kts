import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-script-util:1.4.0")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@/Users/krystian.panek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-util/1.4.0/7a46a84420efbeff02a9e2cb7306bbe288bf01cc/kotlin-script-util-1.4.0.jar")
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
                    "kotlinx.coroutines.*",
                    "net.rubygrapefruit.platform"
            )
        }
    }
}
