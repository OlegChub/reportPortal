package helpers;

public class RequestBuilder {
    private final static String PROJECT_NAME = "superadmin_personal";
    private final static String API_VERSION = "api/v1/";

    public static String urlBuilder(String endpoint) {
        return getApiVersion() + getProjectName() + endpoint;
    }

    public static String getApiVersion() {
        return API_VERSION;
    }

    public static String getProjectName() {
        return PROJECT_NAME;
    }
}
