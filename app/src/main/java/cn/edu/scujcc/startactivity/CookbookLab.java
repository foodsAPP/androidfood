package cn.edu.scujcc.startactivity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Tag;

/**
 * 菜谱数据源
 * 使用了单例模式保证此类仅有一个对象。
 */
public class CookbookLab {
    //单例第1步
    private static CookbookLab INSTANCE = null;

    private List<Cookbook> data;

    private final static String TAG="Cookbook";
    public final static int MSG_HOT_COMMENTS = 2;
    public final static int MSG_ADD_COMMENT = 3;
    public final static int MSG_FAILURE = 4;

    //单例第2步
    private CookbookLab() {
        //初始化空白列表
        data = new ArrayList<>();
        // 删除网络访问
        // getData();
    }

    //单例第3步
    public static CookbookLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CookbookLab();
        }
        return INSTANCE;
    }

    /**
     * 返回数据总数量
     * @return
     */
    public int getSize() {
        return data.size();
    }

    /**
     * 返回指定位置菜谱信息
     * @param position
     * @return
     */
    public Cookbook getCookbook(int position) {
        return this.data.get(position);
    }

    /**
     * 访问网络得到真实数据，代替以前的test()方法
     */
    public void getData(Handler handler) {
        //调用单例
        Retrofit retrofit = RetrofitClient.getInstance();

        CookbookApi api = retrofit.create(CookbookApi.class);
        Call<List<Cookbook>> call = api.getAllCookbooks();
        //enqueue会自己生成子线程，去执行后续代码
        call.enqueue(new Callback<List<Cookbook>>() {
            @Override
            public void onResponse(Call<List<Cookbook>> call,
                                   Response<List<Cookbook>> response) {
                if (null != response && null != response.body()) {
                    Log.d("Cookbook","从阿里云得到的数据是：");
                    Log.d("Cookbook",response.body().toString());
                    data = response.body();
                    //发出通知
                    Message msg = new Message();
                    msg.what = 1; //规定当为1时，代表从阿里云获取数据完毕
                    handler.sendMessage(msg);
                } else {
                    Log.w("Cookbook","response没有数据！");
                }
            }

            @Override
            public void onFailure(Call<List<Cookbook>> call, Throwable t) {
                Log.e("Cookbook","访问网络失败！", t);
            }
        });
    }

    /**
     * 从服务器获取热门评论
     * @param handler
     * @return 热门评论列表
     */
    public List<Comment> getHotComments(String cookbookId,Handler handler) {
        List<Comment> result = null;
        //TODO 使用Retrofit从服务器获取热门评论
        //调用单例
        Retrofit retrofit = RetrofitClient.getInstance();
        CookbookApi api = retrofit.create(CookbookApi.class);
        Call<List<Comment>> call = api.getHotComments(cookbookId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (null != response && null != response.body()) {
                    Log.d("Cookbook", "从阿里云得到的数据是：");
                    Log.d("Cookbook", response.body().toString());
                    List<Comment> comments = response.body();
                    //发出通知
                    Message msg = new Message();
                    msg.what = 2; //规定当为2时，代表从阿里云获取数据完毕
                    msg.obj = comments;
                    handler.sendMessage(msg);
                } else {
                    Log.w("Cookbook", "response没有数据！");
                }
            }
            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Cookbook", "访问网络失败！", t);
            }
        });
        return result;
    }

    /**
     * 添加评论
     * @param CookbookId 菜谱编号
     * @param comment 评论对象
     * @param handler 主线程需要一个通讯用的handler
     */
    public void addComment(String cookbookId,Comment comment,Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        CookbookApi api = retrofit.create(CookbookApi.class);
        Call<Cookbook> call = api.addComment(cookbookId,comment);
        call.enqueue(new Callback<Cookbook>() {
            @Override
            public void onResponse(Call<Cookbook> call, Response<Cookbook> response) {
                Log.d(TAG,"新增评论后服务器返回的数据是：");
                Log.d(TAG,response.body().toString());
                Message msg = new Message();
                msg.what = MSG_ADD_COMMENT;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Cookbook> call, Throwable t) {
                Log.e(TAG,"访问网络失败！",t);
                Message msg = new Message();
                msg.what = MSG_FAILURE;
                handler.sendMessage(msg);
            }
        });
    }
}
