package api.request;

import api.BaseRequest;
import org.apache.http.client.methods.HttpPost;

import static helpers.UserAuthConfig.*;

public class PostUserOauth extends BaseRequest {
    public PostUserOauth() {
        super();
        setMethod(HttpPost.METHOD_NAME);
        setUrl("/uat/sso/oauth/token");
        getQueryParams().put("grant_type", getGrantType());
        getQueryParams().put("username", getUserName());
        getQueryParams().put("password", getPassword());
    }
}
