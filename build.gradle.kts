// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins{
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
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}