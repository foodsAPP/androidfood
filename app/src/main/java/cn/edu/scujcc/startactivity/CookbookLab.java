package cn.edu.scujcc.startactivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CookbookLab {
    private static CookbookLab INSTANCE=null;
    private final static String TAG="COOKBOOK";//定义常量
    public final static int MSG_GET_ALLCHANNEL=1;
    public final static int MSG_HOT_COMMENT=2;
    public final static int MSG_ADD_COMMENT=3;
    public final static int MSG_FAILUER=4;
    private List<Cookbook> date;
    private CookbookLab(){
        //cog网络获得数据
        date=new ArrayList<>();
//        getDate();
//        test();
    }

    public static CookbookLab getInstance(){
        if (INSTANCE==null){
            INSTANCE=new CookbookLab();
        }
        return  INSTANCE;
    }
//    public void test(){
//        date=new ArrayList<>();
//        Channel c =new Channel();
//        c.setTitle("中央一台 综合频道");
//        c.setQuality("1080p");
//        c.setCctv(R.drawable.img2);
//        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");
//        date.add(c);
//        c=new Channel();
//        c.setTitle("中央二台 财经频道");
//        c.setQuality("1080p");
//        c.setCctv(R.drawable.img3);
//        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv2hd.m3u8");
//        date.add(c);
//        c=new Channel();
//        c.setTitle("中央三台 综艺频道");
//        c.setQuality("1080p");
//        c.setCctv(R.drawable.img3);
//        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8");
//        date.add(c);
//        c=new Channel();
//        c.setTitle("中央四台 动画频道");
//        c.setQuality("1080p");
//        c.setCctv(R.drawable.img4);
//        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv4hd.m3u8");
//        date.add(c);
//        for (int i=0;i<10;i++){
//            c=new Channel();
//            c.setTitle("中央五台 体育频道");
//            c.setQuality("1080p");
//            c.setCctv(R.drawable.img5);
//            c.setUrl("http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8");
//            date.add(c);
//        }
//
//    }
    public int getSize(){
        return date.size();
    }
    public Cookbook  getChannel(int position){
        return this.date.get(position);
    }

    public void getDate(Handler handler){
        Retrofit retrofit=RetrofitClient.getINSTANCE();//单例模式
//                new Retrofit.Builder()
//                .baseUrl("http://47.112.255.121:8080")
//                .addConverterFactory(MoshiConverterFactory.create())//转换为Jason格式
//                .build();
        CookbookApi api=retrofit.create(CookbookApi.class);
        Call<cn.edu.scujcc.startactivity.Response<List<Cookbook>>> call=api.getAllcookbook();
        //enqueue会自动生成子线程
        call.enqueue(new Callback<cn.edu.scujcc.startactivity.Response<List<Cookbook>>>() {
            @Override
            public void onResponse(Call<cn.edu.scujcc.startactivity.Response<List<Cookbook>>> call, Response<cn.edu.scujcc.startactivity.Response<List<Cookbook>>> response) {
              if (response.code()==403){
                  Log.d(TAG,"禁止访问");
                  Message msg=new Message();
                  msg.what=MSG_FAILUER;
                  handler.sendMessage(msg);
              }
                if (null != response && null != response.body()){
                    Log.d(TAG,"从阿里云打印到数据");
                    Log.d(TAG,response.body().toString());
                    cn.edu.scujcc.startactivity.Response<List<Cookbook>> result=response.body();
                    date=result.getData();

                    //发出通知
                    Message msg=new Message();
                    msg.what=MSG_GET_ALLCHANNEL;
                    handler.sendMessage(msg);
                }else {
                    Log.w(TAG,"没有数据");
                }
            }

            @Override
            public void onFailure(Call<cn.edu.scujcc.startactivity.Response<List<Cookbook>>> call, Throwable t) {
                Log.d(TAG,"访问网络失败",t);
            }
        });
    }

    public List<Comment> gethotComments(String channelId,Handler handler){
        List<Comment> result=null;
        Retrofit retrofit=RetrofitClient.getINSTANCE();
        CookbookApi api=retrofit.create(CookbookApi.class);
        Call<cn.edu.scujcc.startactivity.Response<List<Comment>>> call=api.gethotComments(channelId);
        call.enqueue(new Callback<cn.edu.scujcc.startactivity.Response<List<Comment>>>() {
            @Override
            public void onResponse(Call<cn.edu.scujcc.startactivity.Response<List<Comment>>> call, Response<cn.edu.scujcc.startactivity.Response<List<Comment>>> response) {
                if (null != response && null != response.body()){
                    Log.d(TAG,"从阿里云打印到数据");
                    Log.d("DianDian",response.body().toString());
                    List<Comment> comments= response.body();

                    //发出通知
                    Message msg=new Message();
                    msg.what=MSG_HOT_COMMENT;
                    msg.obj=comments;
                    handler.sendMessage(msg);
                }else {
                    Log.w(TAG,"没有数据");
                }
            }

            @Override
            public void onFailure(Call<cn.edu.scujcc.startactivity.Response<List<Comment>>> call, Throwable t) {
                Log.d(TAG,"访问网络失败",t);
            }
        });

        return  result;
    }
    //新增评论
    public  void addComment(String channelId,Comment comment,Handler handler){
        Retrofit retrofit=RetrofitClient.getINSTANCE();
        CookbookApi api=retrofit.create(CookbookApi.class);
        Call<Comment> call=api.addComment(channelId,comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Log.d(TAG,"新增评论返回的数据");
                Log.d("DianDian",response.body().toString());
                Message msg=new Message();
                msg.what=MSG_ADD_COMMENT;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.d(TAG,"访问网络失败",t);
                Message msg=new Message();
                msg.what=MSG_FAILUER;
                handler.sendMessage(msg);
            }
        });
    }
}
