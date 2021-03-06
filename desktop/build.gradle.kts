import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "fr.o80.twitckbot.desktop.MainKt"
        javaHome = System.getenv("JAVA15_HOME")

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TwitckBot"

            windows {
                menu = true
                // TODO see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "AF792DA6-2EA3-495A-95E5-C3C6CBCB9948"
            }

            macOS {
                // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                bundleID = "fr.o80.twitckbot.desktop"
            }
        }
    }
}
