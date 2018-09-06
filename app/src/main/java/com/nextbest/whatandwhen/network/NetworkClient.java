package com.nextbest.whatandwhen.network;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nextbest.whatandwhen.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient implements Interceptor {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer";
    private static final String PATH = BuildConfig.PATH;
    private static final String API_PATH = PATH + "/";
    private final int CONNECTION_TIMEOUT = 10;
    private String userToken;
    private ApiService apiService;
    private Application application;
    private OkHttpClient okHttpClient;
    private Runnable logoutRunnable;


    public NetworkClient(Application application) {
        this.application = application;
        createRetrofitConnection();
    }

    /**
     * create retrofit connection
     */
    private void createRetrofitConnection() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.addInterceptor(this);
        client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .serializeNulls()
                .create();

        okHttpClient = client.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    /**
     * get response
     * if response is unauthorized call logout
     *
     * @param chain - Chain
     * @return Response - request response
     * @throws IOException
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder newRequest = originalRequest.newBuilder()
                .method(originalRequest.method(), originalRequest.body());
        if (userToken != null && !userToken.trim().isEmpty()) {
            newRequest.addHeader(AUTHORIZATION_HEADER, "Bearer " + userToken);
        }

        Response response = chain.proceed(newRequest.build());
        //unocmment if logout needed
//        if (response.code() == ErrorStatus.HTTP_RESPONSE_STATUS_UNAUTHORIZED) {
//
//        }
        return response;
    }


    /**
     * get user token
     *
     * @return - String user token
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * set user token
     *
     * @param userToken - String user token
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * get rest service
     *
     * @return RestService
     */
    public ApiService getService() {
        return apiService;
    }
}
