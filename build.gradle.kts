plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    `maven-publish`
    signing
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allJava)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
}

val MAVEN_UPLOAD_USER: String by project
val MAVEN_UPLOAD_PWD: String by project

publishing {
    repositories {
        maven {
            name = "MavenCentral"
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = MAVEN_UPLOAD_USER
                password = MAVEN_UPLOAD_PWD
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("Your Library Name")
                description.set("Short description of your library")
                url.set("https://your.libra.ry/home/page")
                licenses {
                    license {
                        name.set("Licence Name")
                        url.set("http://link.to/full/license/text")
                    }
                }
                developers {
                    developer {
                        id.set("some social id")
                        name.set("Your Full Name")
                        email.set("your@email.address")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/your/project.git")
                    developerConnection.set("scm:git:https://github.com/your/project.git")
                    url.set("https://your-library-web-site.domain")
                }

            }
        }
    }
}

signing {
    val PGP_SIGNING_KEY: String? by project
    val PGP_SIGNING_PASSWORD: String? by project
    useInMemoryPgpKeys(PGP_SIGNING_KEY, PGP_SIGNING_PASSWORD)
    sign(publishing.publications["mavenJava"])
}
