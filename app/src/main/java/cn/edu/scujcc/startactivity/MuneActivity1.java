//package cn.edu.scujcc.startactivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bumptech.glide.Glide;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.hls.HlsMediaSource;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.upstream.DataSource;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//
//import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import bean.MuneBean;
//
//public class MuneActivity1 extends AppCompatActivity {private TextView textView;
//    private SimpleExoPlayer player;
//    private Cookbook cookChannel;
//    private TextView tvName,tvQuality;
//    private PlayerView playerView;
//    private ImageView sendbutton;
//    private  final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private CookbookLab lab=CookbookLab.getInstance();
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            switch (msg.what){
//                case CookbookLab.MSG_HOT_COMMENT:
//                    if (msg.obj!=null){
//                        List<Comment> hotcomments=(List<Comment>)msg.obj;
//                        updateHotcommets(hotcomments);
//                    }
//                    break;
//                case CookbookLab.MSG_ADD_COMMENT:
//                    Toast.makeText(MuneActivity1.this,"感谢你的留言",
//                            Toast.LENGTH_LONG).show();
//                    break;
//                case CookbookLab.MSG_FAILUER:
//                    Toast.makeText(MuneActivity1.this,"请稍后再试",
//                            Toast.LENGTH_LONG).show();
//                    break;
//            }
//
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
//        setContentView(R.layout.activity_mune);
//        Serializable s=getIntent().getSerializableExtra("channel");
//        if (s != null && s instanceof Cookbook){
//            cookChannel=(Cookbook) s;
//            sendbutton=findViewById(R.id.channel_rv);
//            sendbutton.setOnClickListener(v->{
//                EditText t=findViewById(R.id.channel_rv);
//                Comment c=new Comment();
//                c.setAuthor("myapp");
//                c.setContent(t.getText().toString());
//                //随机数
//                Random random=new Random();
//                c.setStar(random.nextInt(100));
//                lab.addComment(cookChannel.getId(),c,handler);
//            });
//
//        }
//        updateUI();
//    }
//
//    private void updateUI() {
//        tvName= findViewById(R.id.channel_rv);
//        tvQuality=findViewById(R.id.channel_rv);
//        tvName.setText(cookChannel.getTitle());
//        tvQuality.setText(cookChannel.getQuality());
//    }
//    public void updateHotcommets(List<Comment> hotcomments){
//        if (hotcomments!=null && hotcomments.size()>0){
//            Comment c1=hotcomments.get(0);
//            TextView username1=findViewById(R.id.t1);
//            username1.setText(c1.getAuthor());
//            TextView date1=findViewById(R.id.t2);
//            date1.setText(dateFormat.format(c1.getDt()));
//            TextView content1=findViewById(R.id.t3);
//            content1.setText(c1.getContent());
//            TextView star1=findViewById(R.id.t4);
//            star1.setText(c1.getStar()+"");
//        }
//        if (hotcomments!=null && hotcomments.size()>1){
//            Comment c1=hotcomments.get(1);
//            TextView username1=findViewById(R.id.t6);
//            username1.setText(c1.getAuthor());
//            TextView date1=findViewById(R.id.t7);
//            date1.setText(dateFormat.format(c1.getDt()));
//            TextView content1=findViewById(R.id.t8);
//            content1.setText(c1.getContent());
//            TextView star1=findViewById(R.id.t9);
//            star1.setText(c1.getStar()+"");
//        }
//        if (hotcomments!=null && hotcomments.size()>2){
//            Comment c1=hotcomments.get(2);
//            TextView username1=findViewById(R.id.t10);
//            username1.setText(c1.getAuthor());
//            TextView date1=findViewById(R.id.t13);
//            date1.setText(dateFormat.format(c1.getDt()));
//            TextView content1=findViewById(R.id.t11);
//            content1.setText(c1.getContent());
//            TextView star1=findViewById(R.id.t12);
//            star1.setText(c1.getStar()+"");
//        }
//    }
//    //销毁
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        clean();
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        init();
//        if (playerView != null){
//            playerView.onResume();
//        }
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (playerView != null){
//            playerView.onPause();
//        }
//        clean();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (player==null){
//            init();
//            if (playerView != null){
//                playerView.onResume();
//            }
//        }
//        lab.gethotComments(cookChannel.getId(),handler);
//    }
//
//    private void init() {
//        player= ExoPlayerFactory.newSimpleInstance(this);
//        player.setPlayWhenReady(true);
//        PlayerView playerView=findViewById(R.id.tv_player);
//        playerView.setPlayer(player);
//
//        Uri vedeoUrl=Uri.parse("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");
//        if (null != cookChannel){
//            vedeoUrl=Uri.parse(cookChannel.getUrl());
//        }
//
//        DataSource.Factory factory=new DefaultDataSourceFactory(this,"DianDian");
//        MediaSource videoSource=new HlsMediaSource.Factory(factory).createMediaSource(vedeoUrl);
//        player.prepare(videoSource);
//    }
//    //退出时关闭播放
//    private  void clean(){
//        if (player != null){
//            player.release();
//        }
//    }
//}
