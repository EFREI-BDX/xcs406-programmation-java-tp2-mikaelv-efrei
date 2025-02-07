plugins {
    java
}

group = "com.efrei.xcs406"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

tasks.test {
    useJUnitPlatform()
    ignoreFailures = true  // Continue même si des tests échouent
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            if (desc.parent == null) {
                val total = result.testCount
                val passed = result.successfulTestCount
                println("\nTest Summary:")
                println("Total tests: $total")
                println("Passed tests: $passed")
                println("Failed tests: ${result.failedTestCount}")
                println("Skipped tests: ${result.skippedTestCount}")
                println("Success rate: ${(passed.toDouble() / total * 100).toInt()}%")
                println("$passed/$total tests completed successfully")
            }
        }))
    }
}