package cn.edu.scujcc.startactivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserLab {
    private final static String TAG="COOKBOOK";
    private static UserLab INSTANCE;
    public final static int MSG_LOGINSUCCESS=1;
    public final static int MSG_PASSWORD_EORRE=-1;
    public final static int MSG_NETWORK_EORRE=-2;
    public final static int REGISTER_FALIU=-2;
    public final static int REGSTER_SUCCESS=2;
    public final static String USER_CURRENT="USER_CURRENT";
    public final static String  USER_TOKEN="USER_TOKEN";
    private UserLab(){}
    public static  UserLab getInstance(){
        if (INSTANCE==null){
            INSTANCE=new UserLab();
        }
        return  INSTANCE;
    }

    public void login(String username, String password, Handler handler){
        Retrofit retrofit=RetrofitClient.getINSTANCE();
        UserApi api=retrofit.create(UserApi.class);
        Call<cn.edu.scujcc.startactivity.Response<String>> call=api.Login(username,password);
        call.enqueue(new Callback<cn.edu.scujcc.startactivity.Response<String>>() {
            @Override
            public void onResponse(Call<cn.edu.scujcc.startactivity.Response<String>> call,
                                   Response<cn.edu.scujcc.startactivity.Response<String>> response) {
                Log.d(TAG,"成功从阿里云打印到数据"+response.body());
                boolean loginscuccess=false;
                String token=null;
                if (response.body()!=null){
                    cn.edu.scujcc.startactivity.Response<String> result=response.body();
                    if (result.getStatus()== cn.edu.scujcc.startactivity.Response.STATUS_OK){
                        loginscuccess=true;
                        token=result.getData();
                    }
                }
                if (loginscuccess){
                    Message msg=new Message();
                    msg.what=MSG_LOGINSUCCESS;
                    msg.obj=token;
                    handler.sendMessage(msg);
                }else {
                    Message msg=new Message();
                    msg.what=MSG_PASSWORD_EORRE;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<cn.edu.scujcc.startactivity.Response<String>> call, Throwable t) {
                Log.d(TAG,"登录失败",t);
                Message msg=new Message();
                msg.what=MSG_NETWORK_EORRE;
                handler.sendMessage(msg);
            }
        });
    }

    public void register(User user , Handler handler){
        Retrofit retrofit=RetrofitClient.getINSTANCE();
        UserApi api=retrofit.create(UserApi.class);
        Call<User> call=api.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG,"用户注册成功");
                Log.d("DianDian",response.body().toString());
                Message msg=new Message();
                msg.what=REGSTER_SUCCESS;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG,"用户注册失败");
                Message msg=new Message();
                msg.what=REGISTER_FALIU;
                handler.sendMessage(msg);
            }
        });
    }
}


