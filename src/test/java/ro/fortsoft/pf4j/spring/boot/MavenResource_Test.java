package ro.fortsoft.pf4j.spring.boot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.cloud.deployer.resource.maven.MavenResource;

public class MavenResource_Test {

    @Test
    public void parsesPluginArtifactCoordinatesWithoutDownloading() {
        MavenResource resource = MavenResource.parse("com.example.plugins:sample-plugin:1.2.3");

        assertEquals("com.example.plugins", resource.getGroupId());
        assertEquals("sample-plugin", resource.getArtifactId());
        assertEquals("1.2.3", resource.getVersion());
        assertEquals("jar", resource.getExtension());
    }

    @Test
    public void buildsPluginArtifactCoordinatesWithoutDownloading() {
        MavenResource resource = new MavenResource.Builder()
                .groupId("com.example.plugins")
                .artifactId("sample-plugin")
                .version("2.0.0")
                .build();

        assertEquals("com.example.plugins", resource.getGroupId());
        assertEquals("sample-plugin", resource.getArtifactId());
        assertEquals("2.0.0", resource.getVersion());
    }
}
