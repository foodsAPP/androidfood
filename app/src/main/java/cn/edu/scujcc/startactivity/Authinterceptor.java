package cn.edu.scujcc.startactivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//拦截器
public class Authinterceptor implements Interceptor {
    private MyPreference preference=MyPreference.getInstance();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authRequest=originalRequest.newBuilder()
                .addHeader("token",preference.token())
                .build();
        return chain.proceed(authRequest);
    }
}
