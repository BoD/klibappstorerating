plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.3"
}

rootProject.name = "klibappstorerating-root"

include(":library")
project(":library").name = "klibappstorerating"

include(":sample")
