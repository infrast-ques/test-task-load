plugins {
    kotlin("jvm") version "1.9.23"
}

group = "my"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("org.apache.commons:commons-lang3:3.17.0")

    implementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    implementation("org.junit.jupiter:junit-jupiter:5.11.3")

    testImplementation(kotlin("test"))
    implementation("org.apache.jmeter:ApacheJMeter_core:5.6.3")
    implementation("us.abstracta.jmeter:jmeter-java-dsl:1.29.1")
    implementation("us.abstracta.jmeter:jmeter-java-dsl-dashboard:1.29.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}