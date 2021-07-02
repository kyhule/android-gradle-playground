import com.project.starter.easylauncher.filter.ColorRibbonFilter.Gravity.BOTTOM
import io.github.reactivecircus.appversioning.toSemVer

plugins {
    id("android-app-conventions")
    id("com.starter.easylauncher") version "4.0.0"
    id("io.github.reactivecircus.app-versioning") version "0.9.1"
}

/*
 * A Gradle Plugin for lazily generating Android app's versionCode & versionName from Git tags.
 * https://github.com/ReactiveCircus/app-versioning
 */
appVersioning {
    overrideVersionCode { gitTag, _, _ ->
        val semVer = gitTag.toSemVer()
        semVer.major * 10_000_000 + semVer.minor * 100_000 + semVer.patch * 1_000 + gitTag.commitsSinceLatestTag
    }

    overrideVersionName { gitTag, _, _ ->
        val semVer = gitTag.toSemVer()
        val preRelease = if (semVer.preRelease != null) "-${semVer.preRelease}" else ""
        val commitsSinceTag = if (gitTag.commitsSinceLatestTag > 0) ".${gitTag.commitsSinceLatestTag}" else ""
        val commitHash = if (gitTag.commitsSinceLatestTag > 0) "+${gitTag.commitHash}" else ""

        "${semVer.major}.${semVer.minor}.${semVer.patch}$preRelease$commitsSinceTag$commitHash"
    }
}

android {
    defaultConfig {
        applicationId = "com.github.kyhule.gradle"
    }
}

easylauncher {
    buildTypes {
        register("debug") {
            filters(customRibbon(gravity = BOTTOM, ribbonColor = "#6138f5"))
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)
}
