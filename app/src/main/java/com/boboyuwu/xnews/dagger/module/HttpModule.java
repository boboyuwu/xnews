package com.boboyuwu.xnews.dagger.module;

import android.content.Context;

import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.xnews.common.constants.ConstantsPath;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wubo on 2017/8/29.
 */

@Module
public class HttpModule {

    //默认缓存失效时间
    private final int mMaxCacheAge = 36000;

    @Singleton
    @Provides
    public OkHttpClient.Builder provideOKHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOKHttpClient(final OkHttpClient.Builder builder, final Context context) {
        //设置OKHttp网络、缓存拦截策略
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                /**
                 * Observes, modifies, and potentially short-circuits requests going out and the corresponding
                 * 观察，修改, 并且可能发生的发出和相应请求短路
                 * requests coming back in. Typically interceptors will be used to add, remove, or transform headers
                 * 请求返回，拦截器常用来添加，移除和转换头信息
                 * on the request or response.
                 * 用在请求和响应上
                 */
                Request request = chain.request();
               /* CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
                cacheBuilder.maxStale(365,TimeUnit.DAYS);//这个是控制缓存的过时时间   这个应该可设置可不设置
                CacheControl cacheControl = cacheBuilder.build();*/
                if (NetworkUtils.isNetAvailable(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response originalResponse = chain.proceed(request);

                if (NetworkUtils.isNetAvailable(context)) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    //String cacheControl = request.cacheControl().toString();
                    int maxAge = 60; // read from cache     有网络的时候缓存60秒失效 先统一缓存设置
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")    //Pragma跟noCache一样最好也移除Cache-Control
                            .header("Cache-Control", "public,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 3; // tolerate 4-weeks stale       没有网络的时候缓存3分钟
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        //缓存文件夹
        File cacheFile = new File(ConstantsPath.PATH_CACHE);
        //缓存大小为50M
        int cacheSize = 50 * 1024 * 1024;
        //创建缓存对象
        Cache cache = new Cache(cacheFile, cacheSize);
        OkHttpClient okHttpClient = builder
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .build();
        return okHttpClient;
    }


    //provide  retrofit
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,Retrofit.Builder builder,String baseUrl){
              return builder
                      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                      .addConverterFactory(GsonConverterFactory.create())
                      .client(okHttpClient)
                      .baseUrl(baseUrl)
                      .build();
    }
}
