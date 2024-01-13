plugins {
  kotlin("jvm")
  id("application")
}

application {
  mainClass = "org.jraf.klibappstorerating.sample.SampleKt"
}

dependencies {
  implementation(project(":klibappstorerating"))
}

// Use "./gradlew run" to run the sample
