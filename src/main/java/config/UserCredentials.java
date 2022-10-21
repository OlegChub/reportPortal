package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/credentials.properties"
})
public interface UserCredentials extends Config {
     String grantType();
     String userName();
     String password();
     String authorizationType();
}
