plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.ktor:ktor-client-core:2.1.2")
                implementation("io.ktor:ktor-client-json:2.1.2")
                //implementation("io.ktor:ktor-client-serialization:2.1.2")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:2.1.2")
                implementation("io.ktor:ktor-client-json-jvm:2.1.2")
                implementation("io.ktor:ktor-client-serialization-jvm:2.1.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")
                //implementation("androidx.constraintlayout:constraintlayout:2.1.0")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                //implementation("io.ktor:ktor-client-ios:2.1.2")
                //implementation("io.ktor:ktor-client-json-native:2.1.2")
                //implementation("io.ktor:ktor-client-serialization-native:2.1.2")
                //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.7.10-native-mt")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.14.0")
            }

        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.starbattle"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
    binaries.all {
        binaryOptions["memoryModel"] = "experimental"
    }
}