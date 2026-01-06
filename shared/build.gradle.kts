import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("maven-publish")
    kotlin("plugin.serialization") version "1.9.24"

}

group = "com.example.kmpdemo.shared"
version = "1.0.0"

kotlin {
    androidTarget() // modern replacement for deprecated android()

    jvmToolchain(17)

    // --- iOS targets ---
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    // XCFramework (optional)
    val iosXCFramework = XCFramework()
    listOf(iosX64, iosArm64, iosSimulatorArm64).forEach { target ->
        target.binaries.framework {
            baseName = "shared"
            binaryOption("bundleId", "com.example.kmpdemo.shared")
            isStatic = true
            iosXCFramework.add(this)
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Shared UI module"
        homepage = "https://example.com"
        ios.deploymentTarget = "16.0"

        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // ✅ Compose Multiplatform (THIS is what you asked for)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("io.ktor:ktor-client-core:2.3.12")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")

                implementation("io.ktor:ktor-client-logging:2.3.12")

                implementation("media.kamel:kamel-image:0.9.4")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.3.5")

                implementation(platform("androidx.compose:compose-bom:2024.09.00"))
                implementation("androidx.compose.ui:ui")
                implementation("androidx.compose.material:material")
                implementation("androidx.compose.ui:ui-tooling-preview")
                implementation("androidx.activity:activity-compose:1.9.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
                implementation("androidx.compose.ui:ui-tooling:1.5.3")

                // ✅ EXPOSE Compose to Flutter app
//                api(platform("androidx.compose:compose-bom:2024.09.00"))
//                api("androidx.compose.ui:ui")
//                api("androidx.compose.material:material")
//                api("androidx.compose.ui:ui-tooling-preview")
//                api("androidx.activity:activity-compose:1.9.2")
//
//                api("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
//                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
//                api(compose.runtime)
//                api(compose.foundation)
//                api(compose.material)
                implementation("io.ktor:ktor-client-darwin:2.3.5")
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }


        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidInstrumentedTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }


        val iosTest by creating {
            dependsOn(commonTest)
        }
    }
}

android {
    namespace = "com.example.kmpdemo.shared"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        // targetSdk removed here (deprecated)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        // Align Java to 17
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

plugins.withId("maven-publish") {
    afterEvaluate {
        publishing {
            repositories {
                mavenLocal()
            }

            publications {
                create<MavenPublication>("androidRelease") {
                    groupId = "com.example.kmpdemo.shared"
                    artifactId = "shared-android"
                    version = "1.0.0"

                    // Explicitly use the android artifact
                    artifact("$buildDir/outputs/aar/shared-release.aar") {
                        builtBy(tasks.named("assembleRelease"))
                    }
                }
            }
        }
    }
}