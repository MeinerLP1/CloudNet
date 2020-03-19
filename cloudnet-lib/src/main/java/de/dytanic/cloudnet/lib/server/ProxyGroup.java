package de.dytanic.cloudnet.lib.server;

import com.google.gson.reflect.TypeToken;
import de.dytanic.cloudnet.lib.interfaces.Nameable;
import de.dytanic.cloudnet.lib.map.WrappedMap;
import de.dytanic.cloudnet.lib.proxylayout.ProxyConfig;
import de.dytanic.cloudnet.lib.server.template.Template;
import de.dytanic.cloudnet.lib.server.version.ProxyVersion;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Tareko on 18.07.2017.
 */
public class ProxyGroup implements Nameable {

    public static final Type TYPE = TypeToken.get(ProxyGroup.class).getType();

    private final String name;
    private final Collection<String> wrapper;
    private final Template template;
    private final ProxyVersion proxyVersion;
    private final int startPort;
    private final int startup;
    private final int memory;
    private final ProxyConfig proxyConfig;
    private final ProxyGroupMode proxyGroupMode;
    private final WrappedMap settings;

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (wrapper != null ? wrapper.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (proxyVersion != null ? proxyVersion.hashCode() : 0);
        result = 31 * result + startPort;
        result = 31 * result + startup;
        result = 31 * result + memory;
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        result = 31 * result + (proxyGroupMode != null ? proxyGroupMode.hashCode() : 0);
        result = 31 * result + (settings != null ? settings.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ProxyGroup that = (ProxyGroup) o;

        if (startPort != that.startPort) {
            return false;
        }
        if (startup != that.startup) {
            return false;
        }
        if (memory != that.memory) {
            return false;
        }
        if (!Objects.equals(name, that.name)) {
            return false;
        }
        if (!Objects.equals(wrapper, that.wrapper)) {
            return false;
        }
        if (!Objects.equals(template, that.template)) {
            return false;
        }
        if (proxyVersion != that.proxyVersion) {
            return false;
        }
        if (!Objects.equals(proxyConfig, that.proxyConfig)) {
            return false;
        }
        if (proxyGroupMode != that.proxyGroupMode) {
            return false;
        }
        return Objects.equals(settings, that.settings);
    }

    @Override
    public String toString() {
        return "ProxyGroup{" +
            "name='" + name + '\'' +
            ", wrapper=" + wrapper +
            ", template=" + template +
            ", proxyVersion=" + proxyVersion +
            ", startPort=" + startPort +
            ", startup=" + startup +
            ", memory=" + memory +
            ", proxyConfig=" + proxyConfig +
            ", proxyGroupMode=" + proxyGroupMode +
            ", settings=" + settings +
            '}';
    }

    public ProxyGroup(String name,
                      Collection<String> wrapper,
                      Template template,
                      ProxyVersion proxyVersion,
                      int startPort,
                      int startup,
                      int memory,
                      ProxyConfig proxyConfig,
                      ProxyGroupMode proxyGroupMode,
                      WrappedMap settings) {
        this.name = name;
        this.wrapper = wrapper;
        this.template = template;
        this.proxyVersion = proxyVersion;
        this.startPort = startPort;
        this.startup = startup;
        this.memory = memory;
        this.proxyConfig = proxyConfig;
        this.proxyGroupMode = proxyGroupMode;
        this.settings = settings;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getMemory() {
        return memory;
    }

    public Template getTemplate() {
        return template;
    }

    public int getStartPort() {
        return startPort;
    }

    public Collection<String> getWrapper() {
        return wrapper;
    }

    public int getStartup() {
        return startup;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    public ProxyGroupMode getProxyGroupMode() {
        return proxyGroupMode;
    }

    public ProxyVersion getProxyVersion() {
        return proxyVersion;
    }

    public WrappedMap getSettings() {
        return settings;
    }
}
