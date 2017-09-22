package zhj.meizigank.api;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhj.meizigank.MyApp;
import zhj.meizigank.util.StateUtils;

public class ApiRetrofit {

    public GankApi GankApiService;
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    public static final String DAILY_BASE_URL = "http://app3.qdaily.com/app3/";


    public GankApi getGankApiService() {
        return GankApiService;
    }


    ApiRetrofit() {
        //cache url
//        File httpCacheDirectory = new File(MyApp.mContext.getCacheDir(), "responses");
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB

        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!StateUtils.isNetworkAvailable(MyApp.mContext)) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();

            }
            Response originalResponse = chain.proceed(request);
            if (StateUtils.isNetworkAvailable(MyApp.mContext)) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .build();


        Retrofit retrofit_gank = new Retrofit.Builder()
                .baseUrl(GANK_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GankApiService = retrofit_gank.create(GankApi.class);
    }

}
