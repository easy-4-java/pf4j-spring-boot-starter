package io.github.easy4j.examples.hello;

import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/** 加载示例插件并检查它已启动的最小 Spring Boot 应用。 */
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Bean
    public ApplicationRunner verifyPlugin(PluginManager pluginManager) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) {
                if (pluginManager.getPlugin("hello-plugin") == null
                        || pluginManager.getPlugin("hello-plugin").getPluginState() != PluginState.STARTED) {
                    throw new IllegalStateException("hello-plugin was not started; check pf4j.plugins-root");
                }
                System.out.println("hello-plugin STARTED");
            }
        };
    }
}
