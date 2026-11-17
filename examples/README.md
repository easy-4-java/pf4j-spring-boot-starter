# 可调试的 PF4J 最小示例

此示例由一个插件 JAR 和一个 Spring Boot 应用组成。Starter 初始化时加载并启动插件，应用随后检查其状态为 `STARTED`；找不到插件时会明确失败。

在仓库根目录使用 JDK 21。当前 `pf4j-extension` 的 3.0.x 源码使用 Maven 4 模型；若需要从源码安装该依赖，应使用 Maven 4：

```bash
mvn -DskipTests install
mvn -f examples/hello-plugin/pom.xml package
mkdir -p examples/hello-app/plugins
cp examples/hello-plugin/target/hello-plugin-1.0.0.jar examples/hello-app/plugins/
cd examples/hello-app
mvn spring-boot:run
```

预期输出包含 `hello-plugin STARTED`。在 `HelloApplication#verifyPlugin` 设置断点，可查看 `PluginManager` 的加载结果。`plugins-root` 相对应用的工作目录，因此需要从 `examples/hello-app` 执行最后一条命令。

Starter 依赖 `io.github.easy4j:pf4j-extension-spring` 和 `pf4j-extension-update` 的 `3.0.x.20260630-SNAPSHOT`。首次构建若提示无法解析这些依赖，先配置组织快照仓库凭据，或在相应源码分支本地安装对应版本；这属于依赖解析问题，不是插件加载失败。
