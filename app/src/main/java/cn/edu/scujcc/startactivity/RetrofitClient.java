package cn.edu.scujcc.startactivity;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getINSTANCE() {
        if (INSTANCE == null) {
            Moshi moshi=new Moshi.Builder()
                    .add(new MyDateAdapter())
                    .build();
            //准备拦截器
            OkHttpClient client=new OkHttpClient.Builder()
                    .addInterceptor(new Authinterceptor())
                    .build();

            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://47.112.255.121:8080")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))//转换为Jason格式
                    .callFactory(client)
                    .build();
        }
        return INSTANCE;
    }


//    public static Retrofit getChannelLab(){
//        if (INSTANCE==null){
//            INSTANCE=new ChannelLab();
//        }
//    }

}
