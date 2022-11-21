package httpclient.client;

public class ClientProvider {
    private static final ThreadLocal<HttpClientBase> THREAD_LOCAL = new ThreadLocal<>();

    private ClientProvider() {
    }

    public static HttpClientBase getHttpClientBase() {
        if (THREAD_LOCAL.get() == null) {
            THREAD_LOCAL.set(new HttpClientBase());
        }
        return THREAD_LOCAL.get();
    }

}
