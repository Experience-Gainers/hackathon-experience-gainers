plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Config.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.AndroidComponents.ktxCore)
    implementation(Dependencies.AndroidComponents.appCompat)
    implementation(Dependencies.AndroidComponents.material)
    implementation(Dependencies.AndroidComponents.constraint)

    testImplementation(Dependencies.TestDependencies.jUnit)
    androidTestImplementation(Dependencies.TestDependencies.jUnitExt)
    androidTestImplementation(Dependencies.TestDependencies.espresso)
}