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
    @GET("/channel/{channelId}/hotcomments")
    Call<Response<List<Comment>>> gethotComments(@Path("channelId") String channelId);

   /*
       新增评论
   * channelId   频道编号
   *comment  评论对象
   * */

    @POST("/cookbook/{channelId}/comment")
    Call<Comment> addComment(@Path("channelId") String channelId, @Body Comment comment);
}
