plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "1.9.24"
    id("com.github.johnrengelman.shadow") version "8.1.0"
    application
}

group = "fun.eternalblue"
version = "1.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //implementation("com.microsoft.playwright:playwright:1.43.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}

application{
    mainClass.set("fun.eternalblue.MainKt")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.set("")
    mergeServiceFiles()
    minimize()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}