package helpers;

public class RequestBuilder {
    private final static String PROJECT_NAME = "superadmin_personal";
    private final static String API_VERSION = "api/v1/";

    public static String buildUrl(String endpoint) {
        return API_VERSION + PROJECT_NAME + endpoint;
    }
}
