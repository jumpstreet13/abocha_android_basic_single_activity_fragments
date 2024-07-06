import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    kotlin("android") version "1.9.24" apply false
    kotlin("plugin.serialization") version "1.9.24" apply false
    id("com.android.application") version "8.5.0" apply false
    id("com.android.library") version "8.5.0" apply false
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