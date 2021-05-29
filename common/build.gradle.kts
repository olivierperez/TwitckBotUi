import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("org.jetbrains.compose") version "0.3.1"
    id("com.android.library")
}

group = "fr.o80.twitck"
version = "1.0"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            val moshiVersion = "1.9.3"
            val ktorVersion = "1.5.0"
            val logbackVersion = "1.2.1"
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api("com.squareup.moshi:moshi:$moshiVersion")
                api("com.squareup.moshi:moshi-adapters:$moshiVersion")
                api("com.squareup.moshi:moshi-kotlin:$moshiVersion")
                kaptt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
                api("io.ktor:ktor-server-netty:$ktorVersion")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-core-jvm:$ktorVersion")
                api("io.ktor:ktor-client-cio:$ktorVersion")
                api("io.ktor:ktor-server-core:$ktorVersion")
                api("io.ktor:ktor-websockets:$ktorVersion")
                api("io.ktor:ktor-client-websockets:$ktorVersion")
                api("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.3.0")
                api("androidx.core:core-ktx:1.5.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun kaptt(dependency: String) {
    configurations["kapt"].dependencies.add(project.dependencies.create(dependency))
}
