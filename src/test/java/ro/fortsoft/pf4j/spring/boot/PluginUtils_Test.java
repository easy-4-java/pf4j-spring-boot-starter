package ro.fortsoft.pf4j.spring.boot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import ro.fortsoft.pf4j.PluginManager;
import ro.fortsoft.pf4j.spring.boot.ext.utils.PluginUtils;

public class PluginUtils_Test {

    @Test
    public void startsEachLoadedPluginOnce() {
        PluginManager manager = mock(PluginManager.class);
        when(manager.loadPlugin(Paths.get("first.jar"))).thenReturn("first");
        when(manager.loadPlugin(Paths.get("second.jar"))).thenReturn("second");

        PluginUtils.loadAndStartPlugins(manager, Arrays.asList("first.jar", "second.jar"));

        verify(manager, times(1)).startPlugin("first");
        verify(manager, times(1)).startPlugin("second");
    }

    @Test
    public void doesNotStartWhenLoadReturnsNoId() {
        PluginManager manager = mock(PluginManager.class);
        when(manager.loadPlugin(Paths.get("missing.jar"))).thenReturn(null);

        PluginUtils.loadAndStartPlugins(manager, Arrays.asList("missing.jar"));

        verify(manager, times(1)).loadPlugin(Paths.get("missing.jar"));
        org.mockito.Mockito.verifyNoMoreInteractions(manager);
    }
}
