plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.housingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.housingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.android.gms:play-services-location:17.0.0")

    testImplementation ("junit:junit:4.12")

    androidTestImplementation ("androidx.test.ext:junit:1.1.1")

    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

        implementation ("androidx.room:room-runtime:$2.5.1")
        annotationProcessor ("androidx.room:room-compiler:2.5.1" )

    }

