<a id="readme-top"></a>

<div align="center">

# pf4j-spring-boot-starter

**Spring Boot Starter for pf4j**

[![Maven Central](https://img.shields.io/maven-central/v/io.github.easy4j/pf4j-spring-boot-starter)](https://github.com/easy-4-java/pf4j-spring-boot-starter)
[![Java](https://img.shields.io/badge/Java-17-orange)](#3-requirements-and-compatibility)
[![License](https://img.shields.io/badge/license-Apache-2.0-green)](https://www.apache.org/licenses/LICENSE-2.0)

[简体中文](./README.zh-CN.md) | [English](./README.md)

[Positioning](#1-positioning) · [Capabilities](#2-core-capabilities) ·
[Dependency](#5-dependency) · [Quick Start](#6-quick-start) ·
[Configuration](#7-configuration-reference) · [Versions](#8-version-lines-and-compatibility) ·
[Build](#9-build-and-test) · [License](#12-license)

</div>

---

> **Current Version**：`3.0.x.20260630-SNAPSHOT`<br>
> **JDK Baseline**：`17+`<br>
> **Group ID**：`io.github.easy4j`<br>
> **Artifact ID**：`pf4j-spring-boot-starter`<br>
> **License**：Apache License 2.0<br>

## 1. Positioning

**pf4j-spring-boot-starter** is a Spring Boot starter that integrates **pf4j 3.15.x** for applications using pf4j. It provides auto-configuration, property binding, and ready-to-use beans so that applications can consume pf4j capabilities with minimal setup.

This starter is the **thin auto-configuration layer** on top of [pf4j-extension](https://github.com/easy-4-java/pf4j-extension). All non-Spring-Boot-specific logic (lifecycle, registry, update, security, transactional update) lives in `pf4j-extension-core` / `pf4j-extension-spring` / `pf4j-extension-update`. This starter only wires Spring Boot's auto-configuration to those modules.

| Dimension | Description |
|---|---|
| Type | Spring Boot Starter |
| Consumers | Spring Boot applications using pf4j 3.15.x |
| Core Capabilities | auto-configuration, property binding, ready-to-use beans for pf4j |
| JDK | `17+` |
| Coordinates | `io.github.easy4j:pf4j-spring-boot-starter:3.0.x.20260630-SNAPSHOT` |
| Config Prefix | `pf4j` |

## 2. Core Capabilities

| Capability | Status | Description |
|---|:---:|---|
| Auto-configuration | ✅ Stable | Registers pf4j beans automatically |
| Property Binding | ✅ Stable | Binds `pf4j.*` to `Pf4jProperties`, `pf4j.update.*` to `Pf4jUpdateProperties`, `pf4j.maven.*` to `Pf4jMavenProperties` |
| `PluginManager` bean | ✅ Stable | Created via `pf4j-extension-spring`'s `ExtendedSpringPluginManager` |
| `UpdateManager` bean | ✅ Stable | Created when `pf4j.update.enabled=true` |
| `MavenUpdateRepository` bean | ✅ Stable | Created via `pf4j-extension-update` when `pf4j.maven.enabled=true` |
| `Pf4jDynamicControllerRegistry` bean | ✅ Stable | Delegates to `pf4j-extension-spring` |

## 3. Requirements and Compatibility

| Dependency | Minimum | Evidence |
|---|---:|---|
| JDK | `17` | `pom.xml` |
| Spring Boot | `2.6.0` | `pom.xml` parent |
| Maven | `3.6+` | Maven Enforcer |
| pf4j-extension | `3.0.x.20260630-SNAPSHOT` | `pom.xml` dependency |
| pf4j | `3.15.0` | transitive |
| pf4j-spring | `0.10.0` | transitive |
| pf4j-update | `2.3.0` | transitive |

## 4. Auto-configuration

The starter auto-configures the following beans (all implementations come from `pf4j-extension`):

| Bean | Condition | Missing Behavior |
|---|---|---|
| `Pf4jDynamicControllerRegistry` | classpath + property | not created |
| `PluginManager` | classpath + property | not created |
| `PluginInfoProvider` | classpath + property | not created |
| `MavenUpdateRepository` | classpath + property | not created |
| `UpdateManager` | classpath + property | not created |

Auto-configuration registration:

- `META-INF/spring.factories` (Spring Boot 2.x legacy, current)

## 5. Dependency

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pf4j-spring-boot-starter</artifactId>
    <version>3.0.x.20260630-SNAPSHOT</version>
</dependency>
```

The starter transitively brings in:

- `io.github.easy4j:pf4j-extension-core`
- `io.github.easy4j:pf4j-extension-spring`
- `io.github.easy4j:pf4j-extension-update`
- `org.pf4j:pf4j`
- `org.pf4j:pf4j-spring`
- `org.pf4j:pf4j-update`

## 6. Quick Start

### 6.1 Add dependency

Add the dependency above to your `pom.xml`.

### 6.2 Configure

```yaml
pf4j:
  enabled: true
```

### 6.3 Use the bean

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Then inject the auto-configured bean in your code:

```java
@Autowired
private org.pf4j.spring.extension.registry.Pf4jDynamicControllerRegistry pf4jDynamicControllerRegistry;
```

## 7. Configuration Reference

### 7.1 Config Prefix

`pf4j`

### 7.2 Configuration Items

| Property | Type | Default | Required | Description | Sensitive |
|---|---|---|:---:|---|:---:|
| `pf4j.enabled` | boolean | `false` | No | Enable the starter | No |
| `pf4j.autowire` | boolean | `true` | No | Whether to autowire extensions | No |
| `pf4j.injectable` | boolean | `true` | No | Whether to register extensions as Spring beans | No |
| `pf4j.singleton` | boolean | `true` | No | Whether to return singleton extension instances | No |
| `pf4j.runtime-mode` | enum | `deployment` | No | `development` or `deployment` | No |
| `pf4j.plugins-root` | string | `plugins` | No | Plugin root directory | No |
| `pf4j.plugins` | list | empty | No | Absolute paths of plugins to load at startup | No |
| `pf4j.system-version` | string | `0.0.0` | No | System version for plugin `requires` checks | No |
| `pf4j.exact-version-allowed` | boolean | `false` | No | Allow exact version expressions | No |
| `pf4j.update.enabled` | boolean | `false` | No | Enable the update sub-system | No |
| `pf4j.update.repos-json-path` | string |  | No | Path to a local `repositories.json` | No |
| `pf4j.update.repos-rest-path` | string |  | No | URL of a REST plugin repository | No |
| `pf4j.update.repos` | list | empty | No | Static plugin repositories | No |
| `pf4j.maven.enabled` | boolean | `false` | No | Enable Maven resource resolution | No |

## 8. Version Lines and Compatibility

| Branch | JDK | Spring Boot | pf4j-extension | Starter Version | Status |
|---|---:|---:|---|---|:---:|
| `2.3.x` / `2.7.x` | `8+` | 2.3.x / 2.7.x | `1.0.x.20260630-SNAPSHOT` | `{2.3.x,2.7.x}.20260630-SNAPSHOT` | Maintenance |
| `3.0.x` ~ `3.5.x` | `17` | 3.x | `2.0.x.20260630-SNAPSHOT` | `{3.0.x~3.5.x}.20260630-SNAPSHOT` | Maintenance |
| `4.0.x` / `4.1.x` | `17+` | 4.x | `3.0.x.20260630-SNAPSHOT` | `{4.0.x,4.1.x}.20260630-SNAPSHOT` | Active |

## 9. Build and Test

```bash
mvn clean verify
mvn -pl pf4j-spring-boot-starter -am test
```

## 10. Troubleshooting

| Symptom | Diagnosis | Resolution |
|---|---|---|
| Bean not created | Check auto-configuration report | Verify `pf4j.enabled=true` and classpath |
| `ClassNotFoundException` | Missing dependency | Add the required `pf4j-extension-*` module |
| Version conflict | `mvn dependency:tree` | Use BOM for version alignment |

## 11. Contribution

1. Fork the repository.
2. Create a feature branch.
3. Run `mvn clean verify` before submitting.
4. Submit a pull request.

## 12. License

This project is licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

---

<div align="center">

[Back to top](#readme-top) · [Issues](https://github.com/easy-4-java/pf4j-spring-boot-starter/issues) · [Repository](https://github.com/easy-4-java/pf4j-spring-boot-starter)

</div>
