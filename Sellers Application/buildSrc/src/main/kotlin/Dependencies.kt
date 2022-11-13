/**
 * Dependency file. It lists all the dependencies used in the application.
 * All dependencies are divided into logical and represent a pair
 * @param objectName The original or semantic name of the dependencies
 *
 * In object:
 * @param version Version of the library used
 * @param dependency Provided dependency
 */
object Dependencies {
    object AndroidComponents {
        private const val ktxCoreVersion = "1.9.0"
        private const val appCompatVersion = "1.5.1"
        private const val materialVersion = "1.7.0"
        private const val constraintVersion = "2.1.4"

        const val ktxCore = "androidx.core:core-ktx:$ktxCoreVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val constraint = "androidx.constraintlayout:constraintlayout:$constraintVersion"
    }

    object TestDependencies {
        private const val jUnitVersion = "4.13.2"
        private const val jUnitExtVersion = "1.1.3"
        private const val espressoCore = "3.4.0"

        //testImplementation
        const val jUnit = "junit:junit:$jUnitVersion"

        //androidTestImplementation
        const val jUnitExt = "androidx.test.ext:junit:$jUnitExtVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoCore"
    }

    object Navigation {
        private const val navigationVersion = "2.5.3"
        private const val safeArgsVersion = "2.5.3"

        const val navigation = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$safeArgsVersion"
    }

    object Lifecycle {
        private const val lifecycleVersion = "2.5.1"

        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    }

    object Koin {
        private const val koinVersion = "3.2.2"

        const val koinCore = "io.insert-koin:koin-core:$koinVersion"
        const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.4"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val okHttpVersion = "4.10.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Gson {
        private const val gsonVersion = "2.10"

        const val gson = "com.google.code.gson:gson:$gsonVersion"
    }

    object ZxingEmbedded {
        private const val zxingEmbeddedVersion = "4.3.0"

        const val zxingEmbedded = "com.journeyapps:zxing-android-embedded:$zxingEmbeddedVersion"
    }

    object QR {
        private const val quickieVersion = "1.5.2"
        const val quickie = "io.github.g00fy2.quickie:quickie-bundled:$quickieVersion"
    }

}