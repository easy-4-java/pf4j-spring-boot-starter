package io.github.easy4j.examples.hello;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/** 用于演示 PF4J 插件的加载和启动。 */
public class HelloPlugin extends Plugin {

    public HelloPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
}
