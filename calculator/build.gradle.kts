plugins {
    java
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "me.soknight.studying"
version = "0.0.1"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springframework.spring.boot.starter.web)

    compileOnly(libs.projectlombok.lombok)
    annotationProcessor(libs.projectlombok.lombok)
}
