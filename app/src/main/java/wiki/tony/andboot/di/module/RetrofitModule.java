package wiki.tony.andboot.di.module;

import android.os.Build;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wiki.tony.andboot.AppEnv;
import wiki.tony.andboot.bean.Apis;

/**
 * Created by Tony on 3/19/16.
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    Interceptor interceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // app/1.0.0 (android; 4.4.4; 19)
                String userAgent = "app/" + AppEnv.VERSION_NAME + " (android; " + Build.VERSION.RELEASE + "; " + Build.VERSION.SDK_INT + ")";
                // 1.0.0
                String version = AppEnv.VERSION_NAME;
                String token = "";

                Request request = chain
                        .request()
                        .newBuilder()
                        .addHeader("User-Agent", userAgent)
                        .addHeader("version", version)
                        .addHeader("token", token)
                        .addHeader("platform", "android")
                        .build();
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient(Interceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        builder.registerTypeAdapter(Date.class, new DateFormatter());
        return builder.create();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient httpClient, Gson gson) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Apis.BASE_API)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit;
    }


}
