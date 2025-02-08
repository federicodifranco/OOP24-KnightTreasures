plugins {

    // Plugin per Java e Applicazione
    java
    application

    // Plugin per creare un JAR eseguibile
    id("com.github.johnrengelman.shadow") version "7.1.2"

    // Plugin per l'analisi della qualità del codice (PMD, Checkstyle, SpotBugs, etc.)
    id("org.danilopianini.gradle-java-qa") version "1.91.0"

    id("com.github.spotbugs") version "5.0.14"
}

spotbugs {
    ignoreFailures.set(true)
    effort.set(com.github.spotbugs.snom.Effort.MAX)
    reportLevel.set(com.github.spotbugs.snom.Confidence.LOW)
}

checkstyle {
    toolVersion = "10.3.1"
}

repositories {
    // Maven Central per le dipendenze
    mavenCentral()
}

dependencies {
    // SpotBugs annotations (deve essere 'annotationProcessor' o 'implementation')
    implementation("com.github.spotbugs:spotbugs-annotations:4.7.3")

    val jUnitVersion = "5.9.1"
    // Dipendenze per JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

application {
    // Definizione della main class
    mainClass.set("it.unibo.knightreasures.heart.KnightTreasures")
}

tasks.named<Test>("test") {
    // Usare JUnit Platform per i test
    useJUnitPlatform()
}
