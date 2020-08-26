import com.cognifide.gradle.aem.bundle.tasks.bundle

plugins {
    id("com.cognifide.aem.bundle")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-scripting-compiler:1.4.0")
    compileOnly("org.jetbrains.intellij.deps:asm-all:8.0.1")
    compileOnly(files("/Users/krystian.panek/.gradle/kotlin-build-dependencies/repo/kotlin.build/intellij-core/201.7223.91/artifacts/intellij-core.jar"))
}

tasks {
    jar {
        bundle {
            attribute("Include-Resource", "@kotlin-scripting-compiler-1.4.0.jar,@intellij-core.jar")
            exportPackage(
                    "org.jetbrains.kotlin.scripting.*",
                    "org.jetbrains.org.objectweb.asm.*",
                    "com.intellij.*"
            )
            importPackageSuffix.set("*;resolution:=optional")
        }
    }
}
