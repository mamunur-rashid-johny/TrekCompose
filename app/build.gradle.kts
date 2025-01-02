import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.techetronventures.trek"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.techetronventures.trek"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val df: DateFormat = SimpleDateFormat("dd_MMM_yyyy_HH_mm_a")
        val timeStamp = df.format(Date())
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        setProperty("archivesBaseName", "Trek_${versionName}_B_${versionCode}$timeStamp")

        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true
    }


    //<editor-fold desc = "Properties File Related">
    val prodProperties = Properties()
    val prodPropertiesFile = File(rootDir,"prod.properties")
    if(prodPropertiesFile.exists() && prodPropertiesFile.isFile){
        prodPropertiesFile.inputStream().use {
            prodProperties.load(it)
        }
    }

    val stageProperties = Properties()
    val stagePropertiesFile = File(rootDir,"stage.properties")
    if(stagePropertiesFile.exists() && stagePropertiesFile.isFile){
        stagePropertiesFile.inputStream().use {
            stageProperties.load(it)
        }
    }

    val keyProperties = Properties()
    val keyPropertiesFile = File(rootDir,"key.properties")
    if (keyPropertiesFile.exists() && keyPropertiesFile.isFile){
        keyPropertiesFile.inputStream().use { stream ->
            keyProperties.load(stream)
        }
    }

    //</editor-fold>

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"${prodProperties.getProperty("BASE_URL")}\"")
            buildConfigField("String", "SUPPORT_URL", "\"${prodProperties.getProperty("URL_SUPPORT")}\"")
            buildConfigField("String", "TRADING_VIEW_URL", "\"${prodProperties.getProperty("URL_TRADING_VIEW")}\"")
            buildConfigField("String", "STATEMENT_URL", "\"${prodProperties.getProperty("URL_STATEMENT")}\"")
            buildConfigField("String", "WEB_SOCKET_END_POINT", "\"${prodProperties.getProperty("WEB_SOCKET_END_POINT")}\"")
        }

        debug {
            isDebuggable = true
            applicationIdSuffix = ".dev"
            resValue("string","app_name","Trek Debug")

            buildConfigField("String", "BASE_URL", "\"${stageProperties.getProperty("BASE_URL")}\"")
            buildConfigField("String", "SUPPORT_URL", "\"${stageProperties.getProperty("URL_SUPPORT")}\"")
            buildConfigField("String", "TRADING_VIEW_URL", "\"${stageProperties.getProperty("URL_TRADING_VIEW")}\"")
            buildConfigField("String", "STATEMENT_URL", "\"${stageProperties.getProperty("URL_STATEMENT")}\"")
            buildConfigField("String", "WEB_SOCKET_END_POINT", "\"${stageProperties.getProperty("WEB_SOCKET_END_POINT")}\"")
        }

        create("production"){
            initWith(getByName("debug"))
            applicationIdSuffix = ".production"
            resValue("string","app_name","Trek Prod(d)")

            buildConfigField("String", "BASE_URL", "\"${prodProperties.getProperty("BASE_URL")}\"")
            buildConfigField("String", "SUPPORT_URL", "\"${prodProperties.getProperty("URL_SUPPORT")}\"")
            buildConfigField("String", "TRADING_VIEW_URL", "\"${prodProperties.getProperty("URL_TRADING_VIEW")}\"")
            buildConfigField("String", "STATEMENT_URL", "\"${prodProperties.getProperty("URL_STATEMENT")}\"")
            buildConfigField("String", "WEB_SOCKET_END_POINT", "\"${prodProperties.getProperty("WEB_SOCKET_END_POINT")}\"")
        }

        create("stage"){
            initWith(getByName("debug"))
            applicationIdSuffix = ".stage"
            resValue("string","app_name","Trek Stage")

            buildConfigField("String", "BASE_URL", "\"${stageProperties.getProperty("BASE_URL")}\"")
            buildConfigField("String", "SUPPORT_URL", "\"${stageProperties.getProperty("URL_SUPPORT")}\"")
            buildConfigField("String", "TRADING_VIEW_URL", "\"${stageProperties.getProperty("URL_TRADING_VIEW")}\"")
            buildConfigField("String", "STATEMENT_URL", "\"${stageProperties.getProperty("URL_STATEMENT")}\"")
            buildConfigField("String", "WEB_SOCKET_END_POINT", "\"${stageProperties.getProperty("WEB_SOCKET_END_POINT")}\"")
        }

    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

ktlint {
    android = true
    ignoreFailures = false
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
        reporter(ReporterType.JSON)
    }
}

dependencies {
    //<editor-fold desc = "Android Core, ViewModel, Life-Cycle">
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)
    //</editor-fold>

    //<editor-fold desc = "Desuger for backward compatibility">
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    //</editor-fold>

    //<editor-fold desc = "Koin">
    implementation(libs.bundles.koin)
    //</editor-fold>

    //<editor-fold desc = "ktor">
    implementation(libs.bundles.ktor)
    //</editor-fold>

    //<editor-fold desc = "Testing Related">
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    //</editor-fold>

    //<editor-fold desc = "Splash Screen API">
    implementation(libs.androidx.splashscreen)
    //</editor-fold>

    //<editor-fold desc = "Compose Navigation">
    implementation(libs.navigation.compose)
    //</editor-fold>

    //<editor-fold desc = "Lottie Animation">
    implementation(libs.lottie.compose)
    //</editor-fold>

    //<editor-fold desc = "Constrain Layout">
    implementation(libs.androidx.constraintlayout.compose)
    //</editor-fold>

    //<editor-fold desc = "Life-Cycle">
    implementation(libs.lifecycle.process)
    //</editor-fold>

    //<editor-fold desc = "Leak-Canary">
    implementation(libs.leakcanary.android)
    //</editor-fold>

}