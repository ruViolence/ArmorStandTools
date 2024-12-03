plugins {
    `java-library`
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow") version "8.3.1"
    id("io.papermc.paperweight.userdev") version "1.7.3"
}

group = "com.gmail.st3venau.plugins"
version = "4.4.6"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/") // Paper & PlotSquared
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/") // WorldGuard
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.1.0-SNAPSHOT")
    implementation(platform("com.intellectualsites.bom:bom-newest:1.51"))
    compileOnly("com.intellectualsites.plotsquared:plotsquared-core")
    compileOnly("com.intellectualsites.plotsquared:plotsquared-bukkit") { isTransitive = false }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    build {
        dependsOn(shadowJar)
    }
}
