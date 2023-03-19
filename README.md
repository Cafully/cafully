# Cafully

![GitHub](https://img.shields.io/github/license/Cafully/cafully?style=flat-square)
[![Maven Central](https://img.shields.io/maven-central/v/cn.enaium.cafully/cafully-agent?style=flat-square)](https://central.sonatype.com/search?smo=true&q=cafully-api)
[![JitPack](https://img.shields.io/jitpack/version/com.github.Cafully/cafully?style=flat-square)](https://jitpack.io/#Cafully/cafully)

## Usage

Add JVM Options

`-javaagent:/absolute/path/to/cafully-agent.jar`

If you usage `JDK17+` also add:

```
--add-opens=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED
--add-opens=java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED
```

## Plugin

### Usage

Drag the plugin file to the plugin folder

### Custom

[example-cafully-plugin](https://github.com/Cafully/example-cafully-plugin)