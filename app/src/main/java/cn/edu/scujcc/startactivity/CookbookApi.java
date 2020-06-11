package cn.edu.scujcc.startactivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface cookbookApi {
    @GET("/cookbook")
    Call<List<Cookbook>> getAllcookbooks();

    /**
     * 获取热门评论
     * @param cookbookId 频道编号
     * @return 热门评论的列表
     */
    @GET("/cookbook/{cookbookId}/hotcomments")
    Call<List<Comment>> getHotComments(@Path("cookbookId") String cookbookId);

    /**
     * 新增评论
     * @param cookbookId 频道编号
     * @param comment 评论对象
     * @return 频道对象
     */
    @POST("/cookbook/{cookbookId}/comment")
    Call<Cookbook> addComment(@Path("cookbookId") String cookbookId,@Body Comment comment);
}

