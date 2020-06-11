package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bean.MuneBean;

public class MuneActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageView imAdd,imSearch,imClear;
    private EditText evSearch;
    private List<MuneBean> list=new ArrayList<>();// 菜谱的数据集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        setContentView(R.layout.activity_mune);

//        //绑定
//        gridView=findViewById(R.id.cookbook_cstar);
        imAdd=findViewById(R.id.im_add);
        imSearch=findViewById(R.id.im_search);
        imClear=findViewById(R.id.im_clear);
        evSearch=findViewById(R.id.ev_search);

    }
}
