import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}

allprojects {
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