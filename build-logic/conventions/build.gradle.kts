plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}
