plugins {
    id("java")
    id("application")
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("it.unibo.knightreasures.heart.KnightTreasures")
}
