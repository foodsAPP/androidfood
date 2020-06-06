package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class SplashActivity extends AppCompatActivity {

    private TextView tvOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        setContentView(R.layout.activity_lunch);
        //新建个线程，执行延时操作
//        new Thread( new Runnable( ) {
//            @Override
//            public void run() {
//                //耗时任务，比如加载网络数据
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 这里可以睡几秒钟，如果要放广告的话 3000是毫秒，代表的是3秒
//                        sleep(3000);
//                        // 1秒后自动跳转到主页
//                        Intent intent =new Intent(SplashActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        } ).start();

        // 绑定试图中的ID
        tvOver=(TextView) findViewById(R.id.tv_over);
        // 绑定监听事件（响应点击跳过的动作）
        tvOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击后执行的操作
                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}