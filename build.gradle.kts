plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"

    kotlin("plugin.noarg") version "2.2.10"
    kotlin("plugin.jpa") version "2.2.10"

    kotlin("kapt") version "1.9.25" // 추가
}

group = "org.example"
version = "0.0.1-SNAPSHOT"
description = "to-tasty-kotlin"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}
// 추가
val queryDslVersion: String by extra

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.11")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-docker-compose")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    runtimeOnly ("com.h2database:h2")
    runtimeOnly ("com.mysql:mysql-connector-j")
//    runtimeOnly ("org.springframework.boot:spring-boot-docker-compose")

    //jjwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.7")

    //queryDSL
    implementation("io.github.openfeign.querydsl:querydsl-jpa:7.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

noArg{
    annotation("com.example.totastykotlin.domain.meeting.entity.MeetingParticipation")
}

// Querydsl 설정부 추가
val generated = file("src/main/generated")

// querydsl QClass 파일 생성 위치 지정
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(generated)
}

// kotlin source set에 querydsl QClass 위치 추가
sourceSets {
    main {
        kotlin.srcDirs += generated
    }
}

// gradle clean 시에 QClass 디렉토리 삭제
tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}

//kapt {
//    generateStubs = true
//}