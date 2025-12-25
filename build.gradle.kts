// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val objectBox by extra(libs.versions.objectbox.get())
    dependencies {
        classpath("io.objectbox:objectbox-gradle-plugin:$objectBox")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.navigationSafeargs) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.compose.compiler) apply false
}