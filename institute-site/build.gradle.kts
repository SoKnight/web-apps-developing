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
    implementation(libs.springframework.spring.boot.starter.data.jpa)
    implementation(libs.springframework.spring.boot.starter.security)
    implementation(libs.springframework.spring.boot.starter.thymeleaf)
    implementation(libs.springframework.spring.boot.starter.validation)
    implementation(libs.springframework.spring.boot.starter.web)

    implementation(libs.thymeleaf.extras.springsecurity6)

    runtimeOnly(libs.postgresql.postgresql)

    compileOnly(libs.projectlombok.lombok)
    annotationProcessor(libs.projectlombok.lombok)
}
