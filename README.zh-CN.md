<a id="readme-top"></a>

<div align="center">

# pf4j-spring-boot-starter

**Spring Boot Starter，集成 pf4j**

[![Maven Central](https://img.shields.io/maven-central/v/io.github.easy4j/pf4j-spring-boot-starter)](https://github.com/easy-4-java/pf4j-spring-boot-starter)
[![Java](https://img.shields.io/badge/Java-17-orange)](#3-运行要求与兼容性)
[![License](https://img.shields.io/badge/license-Apache-2.0-green)](https://www.apache.org/licenses/LICENSE-2.0)

[English](./README.md) | [简体中文](./README.zh-CN.md)

[项目定位](#1-项目定位) · [核心能力](#2-核心能力) ·
[引入依赖](#5-引入依赖) · [快速开始](#6-快速开始) ·
[配置参考](#7-配置参考) · [版本线](#8-版本线与兼容性) ·
[构建测试](#9-构建与测试) · [许可证](#12-许可证)

</div>

---

> **当前版本**：`3.0.x.20260630-SNAPSHOT`<br>
> **JDK 基线**：`17+`<br>
> **Group ID**：`io.github.easy4j`<br>
> **Artifact ID**：`pf4j-spring-boot-starter`<br>
> **许可证**：Apache License 2.0<br>

## 1. 项目定位

**pf4j-spring-boot-starter** 是一个面向 使用 pf4j 3.15.x 的应用 的 Spring Boot Starter，用于将 **pf4j** 集成到 Spring Boot 应用中。它提供自动装配、属性绑定与开箱即用的 Bean，使应用以最小配置即可使用 pf4j 的全部能力。

本 Starter 是 [pf4j-extension](https://github.com/easy-4-java/pf4j-extension) 之上的 **Spring Boot 自动装配薄壳层**。所有非 Spring Boot 特定的逻辑（生命周期、注册中心、Update、安全校验、事务化更新）均下沉到 `pf4j-extension-core` / `pf4j-extension-spring` / `pf4j-extension-update`。本 Starter 仅负责将 Spring Boot 自动装配与这些模块对接。

| 维度 | 说明 |
|---|---|
| 类型 | Spring Boot Starter |
| 消费方 | 使用 pf4j 3.15.x 的 Spring Boot 应用 |
| 核心能力 | 自动装配、属性绑定、开箱即用的 pf4j Bean |
| JDK | `17+` |
| 坐标 | `io.github.easy4j:pf4j-spring-boot-starter:3.0.x.20260630-SNAPSHOT` |
| 配置前缀 | `pf4j` |

## 2. 核心能力

| 能力 | 状态 | 说明 |
|---|:---:|---|
| 自动装配 | ✅ 稳定 | 自动注册 pf4j 相关 Bean |
| 属性绑定 | ✅ 稳定 | 绑定 `pf4j.*` 到 `Pf4jProperties`，`pf4j.update.*` 到 `Pf4jUpdateProperties`，`pf4j.maven.*` 到 `Pf4jMavenProperties` |
| `PluginManager` Bean | ✅ 稳定 | 通过 `pf4j-extension-spring` 的 `ExtendedSpringPluginManager` 创建 |
| `UpdateManager` Bean | ✅ 稳定 | 当 `pf4j.update.enabled=true` 时创建 |
| `MavenUpdateRepository` Bean | ✅ 稳定 | 通过 `pf4j-extension-update` 创建（`pf4j.maven.enabled=true`） |
| `Pf4jDynamicControllerRegistry` Bean | ✅ 稳定 | 委托给 `pf4j-extension-spring` |

## 3. 运行要求与兼容性

| 依赖 | 最低版本 | 证据来源 |
|---|---:|---|
| JDK | `17` | `pom.xml` |
| Spring Boot | `2.6.0` | `pom.xml` parent |
| Maven | `3.6+` | Maven Enforcer |
| pf4j-extension | `3.0.x.20260630-SNAPSHOT` | `pom.xml` 依赖 |
| pf4j | `3.15.0` | 传递依赖 |
| pf4j-spring | `0.10.0` | 传递依赖 |
| pf4j-update | `2.3.0` | 传递依赖 |

## 4. 自动装配

Starter 自动装配以下 Bean（实现来自 `pf4j-extension`）：

| Bean | 条件 | 缺失时行为 |
|---|---|---|
| `Pf4jDynamicControllerRegistry` | classpath + property | 不创建 |
| `PluginManager` | classpath + property | 不创建 |
| `PluginInfoProvider` | classpath + property | 不创建 |
| `MavenUpdateRepository` | classpath + property | 不创建 |
| `UpdateManager` | classpath + property | 不创建 |

自动装配注册：

- `META-INF/spring.factories`（Spring Boot 2.x 传统方式，当前使用）

## 5. 引入依赖

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pf4j-spring-boot-starter</artifactId>
    <version>3.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Starter 会传递引入：

- `io.github.easy4j:pf4j-extension-core`
- `io.github.easy4j:pf4j-extension-spring`
- `io.github.easy4j:pf4j-extension-update`
- `org.pf4j:pf4j`
- `org.pf4j:pf4j-spring`
- `org.pf4j:pf4j-update`

## 6. 快速开始

### 6.1 引入依赖

在 `pom.xml` 中添加上述依赖。

### 6.2 配置

```yaml
pf4j:
  enabled: true
```

### 6.3 使用 Bean

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

在业务代码中注入自动装配的 Bean：

```java
@Autowired
private org.pf4j.spring.extension.registry.Pf4jDynamicControllerRegistry pf4jDynamicControllerRegistry;
```

## 7. 配置参考

### 7.1 配置前缀

`pf4j`

### 7.2 配置项

| 属性 | 类型 | 默认值 | 必填 | 说明 | 敏感 |
|---|---|---|:---:|---|:---:|
| `pf4j.enabled` | boolean | `false` | 否 | 是否启用 Starter | 否 |
| `pf4j.autowire` | boolean | `true` | 否 | 是否自动注入扩展依赖 | 否 |
| `pf4j.injectable` | boolean | `true` | 否 | 是否将扩展注册为 Spring Bean | 否 |
| `pf4j.singleton` | boolean | `true` | 否 | 扩展是否单例 | 否 |
| `pf4j.runtime-mode` | enum | `deployment` | 否 | `development` 或 `deployment` | 否 |
| `pf4j.plugins-root` | string | `plugins` | 否 | 插件根目录 | 否 |
| `pf4j.plugins` | list | 空 | 否 | 启动时加载的插件绝对路径列表 | 否 |
| `pf4j.system-version` | string | `0.0.0` | 否 | 系统版本，用于插件 `requires` 比较 | 否 |
| `pf4j.exact-version-allowed` | boolean | `false` | 否 | 是否允许精确版本表达式 | 否 |
| `pf4j.update.enabled` | boolean | `false` | 否 | 启用 Update 子系统 | 否 |
| `pf4j.update.repos-json-path` | string |  | 否 | 本地 `repositories.json` 路径 | 否 |
| `pf4j.update.repos-rest-path` | string |  | 否 | REST 插件仓库 URL | 否 |
| `pf4j.update.repos` | list | 空 | 否 | 静态插件仓库列表 | 否 |
| `pf4j.maven.enabled` | boolean | `false` | 否 | 启用 Maven 资源解析 | 否 |

## 8. 版本线与兼容性

| 分支 | JDK | Spring Boot | pf4j-extension | Starter 版本 | 状态 |
|---|---:|---:|---|---|:---:|
| `2.3.x` / `2.7.x` | `8+` | 2.3.x / 2.7.x | `1.0.x.20260630-SNAPSHOT` | `{2.3.x,2.7.x}.20260630-SNAPSHOT` | 维护中 |
| `3.0.x` ~ `3.5.x` | `17` | 3.x | `2.0.x.20260630-SNAPSHOT` | `{3.0.x~3.5.x}.20260630-SNAPSHOT` | 维护中 |
| `4.0.x` / `4.1.x` | `17+` | 4.x | `3.0.x.20260630-SNAPSHOT` | `{4.0.x,4.1.x}.20260630-SNAPSHOT` | 活跃开发 |

## 9. 构建与测试

```bash
mvn clean verify
mvn -pl pf4j-spring-boot-starter -am test
```

## 10. 排障

| 症状 | 诊断 | 解决 |
|---|---|---|
| Bean 未创建 | 查看自动装配报告 | 确认 `pf4j.enabled=true` 与 classpath |
| `ClassNotFoundException` | 缺少依赖 | 引入对应 `pf4j-extension-*` 模块 |
| 版本冲突 | `mvn dependency:tree` | 使用 BOM 统一版本 |

## 11. 贡献

1. Fork 本仓库。
2. 创建特性分支。
3. 提交前运行 `mvn clean verify`。
4. 提交 Pull Request。

## 12. 许可证

本项目采用 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。

---

<div align="center">

[返回顶部](#readme-top) · [问题反馈](https://github.com/easy-4-java/pf4j-spring-boot-starter/issues) · [仓库地址](https://github.com/easy-4-java/pf4j-spring-boot-starter)

</div>
