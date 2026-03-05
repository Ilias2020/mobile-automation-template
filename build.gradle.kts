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
    // framework (src/main/java)
    implementation("io.appium:java-client:9.4.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.33.0")
    implementation("org.testng:testng:7.11.0")

    // tests (src/test/java)
    testImplementation("io.qameta.allure:allure-testng:2.29.1")
    // Лёгкая реализация SLF4J, чтобы убрать warning и видеть логи
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.13")
}

tasks.test {
    useTestNG()

    testLogging {
        showStandardStreams = true
        // showStandardStreams = true — разрешаем выводить System.out / System.err в консоль Gradle
        // Без этого Gradle скрывает println из тестов
    }

    // Пробрасываем -DrunMobile=true из Gradle в JVM, где выполняются тесты
    systemProperty("runMobile", providers.systemProperty("runMobile").orElse("false").get())
}