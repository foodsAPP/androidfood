package cn.edu.scujcc.startactivity;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CookbookApi {

    @GET("/cookbook")
    Call <Response<List<Cookbook>>> getAllcookbook();

   //获得热门评论
    @GET("/cookbook/{channelId}/hotcomments")
    Call<Response<List<Comment>>> gethotComments(@Path("channelId") String channelId);
   //获得新增材料
    @POST("/cookbook/{channelId}/material")
    Call<Response<List<Material>>> getmaterial(@Path("channelId") String channelId,@Body Material material);

    //获得新增步骤
    @POST("/cookbook/{channelId}/step")
    Call<Response<List<Step>>> getstep(@Path("channelId") String channelId,@Body Step step);
   /*
       新增评论
   * channelId   频道编号
   *comment  评论对象
   * */

    @POST("/cookbook/{channelId}/comment")
    Call<Comment> addComment(@Path("channelId") String channelId, @Body Comment comment);
}
