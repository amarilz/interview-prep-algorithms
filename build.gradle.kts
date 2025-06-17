plugins {
    id("java")
    kotlin("jvm")
}

group = "com.amarildo"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains:annotations:24.0.0")
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    testCompileOnly("org.projectlombok:lombok:1.18.38")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.1")
    testImplementation("org.junit.platform:junit-platform-engine:1.12.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
