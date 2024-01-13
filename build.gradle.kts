allprojects {
  repositories {
    mavenCentral()
  }

  group = "org.jraf"
  version = "1.1.1"
}

plugins {
  kotlin("jvm").apply(false)
}

// Run `./gradlew refreshVersions` to update dependencies
