package ui.driverConfig;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/browser.properties"
})
public interface BrowserConfig extends Config {
    String browser();
}
