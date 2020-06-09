package cn.edu.scujcc.startactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreference {
    private  static  MyPreference INSTANCE=null;
    private Context context;
    private SharedPreferences preferences;
    //单例
    private MyPreference(){}
    public  static MyPreference getInstance(){
        if (INSTANCE==null){
            INSTANCE=new MyPreference();
        }
        return INSTANCE;
    }
  public void setup(Context context){
        this.context=context;
      preferences= PreferenceManager.getDefaultSharedPreferences(context);
  }


    public void  saveUser(String username,String token){
      SharedPreferences.Editor editor=preferences.edit();
      editor.putString(UserLab.USER_CURRENT,username);
        editor.putString(UserLab.USER_TOKEN,token);
        editor.apply();//写入用户
    }

    public String currentUser(){
//        params.get("username", preferences.getString("username", ""));
//        params.get("token", String.valueOf(preferences.getString("token","" )));
        return preferences.getString(UserLab.USER_CURRENT,"未登录");

    }
    //读出当前用户
    public  String token(){
        return preferences.getString(UserLab.USER_TOKEN,"未登录");
    }


}
