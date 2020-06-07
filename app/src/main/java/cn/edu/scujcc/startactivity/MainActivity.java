package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 执行延时操作,自动跳转
        TimerTask delayTask = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };
        final Timer timer = new Timer();
        timer.schedule(delayTask,2000);//延时两秒执行 run 里面的操作

        // 绑定试图中的ID
        tvOver=(TextView) findViewById(R.id.tv_over);
        // 绑定监听事件（响应点击跳过的动作）
        tvOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击后执行的跳转页面操作
                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();
            }
        });


    }
}
