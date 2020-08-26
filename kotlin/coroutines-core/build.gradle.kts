import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlinx-coroutines-core-1.3.7.jar")
            exportPackage("kotlinx.coroutines.*")
            excludePackage("android.os", "sun.misc")
        }
    }
}
