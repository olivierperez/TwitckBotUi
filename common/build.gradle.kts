import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("kapt")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
            }
        }
        val androidMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.2")
                implementations(Deps.moshi)
                kaptt(Deps.moshiKapt)
                implementations(Deps.ktor)
                implementations(Deps.log)
            }
        }
        val desktopMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                api(compose.desktop.common)
                implementations(Deps.moshi)
                kaptt(Deps.moshiKapt)
                implementations(Deps.ktor)
                implementations(Deps.log)
            }
        }
    }
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}

fun KotlinDependencyHandler.implementations(dependencies: Array<String>) {
    dependencies.forEach {
        implementation(it)
    }
}

fun kaptt(dependency: String) {
    configurations["kapt"].dependencies.add(project.dependencies.create(dependency))
}
