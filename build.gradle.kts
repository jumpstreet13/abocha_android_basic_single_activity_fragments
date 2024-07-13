import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("jvm") version "1.9.24" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            allWarningsAsErrors = false

            freeCompilerArgs.addAll(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath + "/compose_metrics"
            )

            freeCompilerArgs.addAll(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath + "/compose_metrics"
            )
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}