plugins {
    id("java")
}

group = "com.supplyboost"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("com.itextpdf:itext7-core:7.2.3")
    implementation("org.slf4j:slf4j-simple:1.7.30")
}

tasks.test {
    useJUnitPlatform()
}