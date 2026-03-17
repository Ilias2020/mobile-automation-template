tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

plugins {
    id("java")
    id("io.qameta.allure") version "3.0.2"
}

group = "com.company.mobile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allure {
    version.set("2.36.0")
}

dependencies {
    implementation("io.appium:java-client:9.4.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.33.0")
    testImplementation("org.testng:testng:7.11.0")
    implementation("org.apache.logging.log4j:log4j-core:2.25.3")
    implementation("org.apache.logging.log4j:log4j-api:2.25.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.25.3")
    testImplementation("org.assertj:assertj-core:3.27.7")

    implementation("io.qameta.allure:allure-testng:2.29.1")

    compileOnly("org.projectlombok:lombok:1.18.44")
    annotationProcessor("org.projectlombok:lombok:1.18.44")

    testCompileOnly("org.projectlombok:lombok:1.18.44")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.44")
}

tasks.test {
    useTestNG()

    testLogging {
        events ("passed", "skipped", "failed", "standardOut", "standardError")
        showStandardStreams = true

    }
    // Пробрасываем -DrunMobile=true из Gradle в JVM, где выполняются тесты
    systemProperty("runMobile", providers.systemProperty("runMobile").orElse("false").get())
}
configurations.configureEach {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.slf4j") {
            useVersion("1.7.32")
        }
    }
}