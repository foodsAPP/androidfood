package cn.edu.scujcc.startactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bean.MuneBean;

public class MuneActivity extends AppCompatActivity {
    private ImageView back,upload;
    private CookbookLab lab=CookbookLab.getInstance();
    private ImageView Img;
    private RecyclerView channelRV;
    private CookbookRVAdapter rvAdapter;
    //线程第一步，创建主线程
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case CookbookLab.MSG_GET_ALLCHANNEL:
                    rvAdapter.notifyDataSetChanged();
                    break;
                case CookbookLab.MSG_FAILUER:
                    falied();
                    break;
            }

        }
    };
    public void falied(){
        Toast.makeText(MuneActivity.this,
                "TOKEN无效,禁止访问",
                Toast.LENGTH_LONG
        ).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ;//去掉标题栏
        setContentView(R.layout.activity_mune);

        Img = findViewById(R.id.cookbook_url);
        Img.setOnClickListener(v -> {
            Intent intent = new Intent(MuneActivity.this, CheckActivity.class);
            startActivity(intent);
        });

          upload=findViewById(R.id.im_add);
            upload.setOnClickListener(v->{
                Intent intent=new Intent(MuneActivity.this,UploadActivity.class);
                startActivity(intent);
            });
//        this.channelRV=findViewById(R.id.channel_rv);
//        rvAdapter=new CookbookRVAdapter(MuneActivity.this,p->{
//            Intent intent=new Intent(MuneActivity.this,CheckActivity.class);
//            Cookbook c=lab.getcook(p);
//            intent.putExtra("channel",c);
//            startActivity(intent);
//        });
////        ChannelRVAdapter rvAdapter=new ChannelRVAdapter((ChannelRVAdapter.ChannelClikLisenter) this);
////
//        this.channelRV.setAdapter(rvAdapter);
//        this.channelRV.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //把主线程的handler给子线程用
//        lab.getDate(handler);
//    }

    }
    }

