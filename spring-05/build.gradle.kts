plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("io.freefair.lombok") version "8.0.1"
    id("org.flywaydb.flyway") version "9.16.3"
}

group = "ru.otus.spring"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

flyway {
    url = "jdbc:h2:mem:testdb"
    user = "user"
    password = "user"
    locations = arrayOf("classpath:db/migration")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.shell:spring-shell-starter:3.0.1")
    implementation("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core")
}

tasks.test {
    useJUnitPlatform()
}