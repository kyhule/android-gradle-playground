// TODO Figure out why this was only needed once adding Spotless plugin
buildscript {
    repositories {
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
