package zhj.meizigank.api;

public class ApiFactory {

    protected static final Object monitor = new Object();
    static GankApi gankApiSingleton = null;

    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new ApiRetrofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

}
