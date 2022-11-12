plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
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
            isMinifyEnabled = true
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

    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinAndroid)

    implementation(Dependencies.Coroutines.coroutines)

    implementation(Dependencies.Lifecycle.viewmodel)

    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Navigation.navigationUI)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.okHttp)

    implementation(Dependencies.Gson.gson)
}