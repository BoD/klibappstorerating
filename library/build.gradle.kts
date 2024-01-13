plugins {
    kotlin("jvm")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("signing")
}

tasks {
    // Generate Javadoc (Dokka) Jar
    register<Jar>("dokkaHtmlJar") {
        archiveClassifier.set("javadoc")
        from(layout.buildDirectory.get().dir("dokka"))
        dependsOn(dokkaHtml)
    }

    // Generate Sources Jar
    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }
}

dependencies {
    implementation(KotlinX.coroutines.jdk9)
}

publishing {
    repositories {
        maven {
            // Note: declare your user name / password in your home's gradle.properties like this:
            // mavenCentralNexusUsername = <user name>
            // mavenCentralNexusPassword = <password>
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            name = "mavenCentralNexus"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(tasks.getByName("dokkaHtmlJar"))
            artifact(tasks.getByName("sourcesJar"))
            pom {
                name.set("kLibAppStoreRating")
                description.set("App store rating library for Kotlin.")
                url.set("https://github.com/BoD/klibappstorerating")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("BoD")
                        name.set("Benoit 'BoD' Lubek")
                        email.set("BoD@JRAF.org")
                        url.set("https://JRAF.org")
                        organization.set("JRAF.org")
                        organizationUrl.set("https://JRAF.org")
                        roles.set(listOf("developer"))
                        timezone.set("+1")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/BoD/klibappstorerating")
                    developerConnection.set("scm:git:https://github.com/BoD/klibappstorerating")
                    url.set("https://github.com/BoD/klibappstorerating")
                }
                issueManagement {
                    url.set("https://github.com/BoD/klibappstorerating/issues")
                    system.set("GitHub Issues")
                }
            }
        }
    }
}

signing {
    // Note: declare the signature key, password and file in your home's gradle.properties like this:
    // signing.keyId=<8 character key>
    // signing.password=<your password>
    // signing.secretKeyRingFile=<absolute path to the gpg private key>
    sign(publishing.publications)
}
//
//// Workaround for https://youtrack.jetbrains.com/issue/KT-46466
//val dependsOnTasks = mutableListOf<String>()
//tasks.withType<AbstractPublishToMaven>().configureEach {
//  dependsOnTasks.add(this.name.replace("publish", "sign").replaceAfter("Publication", ""))
//  dependsOn(dependsOnTasks)
//}

tasks.dokkaHtml.configure {
    outputDirectory.set(rootProject.file("docs"))
}

// Run `./gradlew dokkaHtml` to generate the docs
// Run `./gradlew publishToMavenLocal` to publish to the local maven repo
// Run `./gradlew publish` to publish to Maven Central (then go to https://oss.sonatype.org/#stagingRepositories and "close", and "release")
