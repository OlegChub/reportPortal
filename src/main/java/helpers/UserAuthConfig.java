package helpers;

import config.UserCredentials;
import org.aeonbits.owner.ConfigFactory;

public class UserAuthConfig {
    private UserAuthConfig() {
    }
    private static UserCredentials userCredentialsAuthConfig = ConfigFactory.create(UserCredentials.class,System.getProperties());

    public static String getGrantType(){
        return userCredentialsAuthConfig.grantType();
    }
    public static String getUserName(){
        return userCredentialsAuthConfig.userName();
    }
    public static String getPassword(){
        return userCredentialsAuthConfig.password();
    }
    public static String getAuthorizationType(){
        return userCredentialsAuthConfig.authorizationType();
    }
}
