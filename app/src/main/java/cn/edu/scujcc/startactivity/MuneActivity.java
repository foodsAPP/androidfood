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

        // 存放数据
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-10/5b44623809ae7.jpg","美味三文鱼","231"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-04/5b3c92f4d2782.jpg","那些让人爱不释手的食物","2345"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-03/5b3b1bd30ef4e.jpg","即使是一个人也好享受好每一餐","344"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-03/5b3acc36c3539.jpg","超级可爱的小甜心","34"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-06-26/5b31e07b83181.jpg","减肥瘦身蔬菜沙拉","754"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-06-20/5b29bc8910c34.jpg","夏季美味冰淇淋夏季美味冰淇淋","67"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-10/5b44623809ae7.jpg","美味三文鱼","231"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-04/5b3c92f4d2782.jpg","那些让人爱不释手的食物","2345"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-03/5b3b1bd30ef4e.jpg","即使是一个人也好享受好每一餐","344"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-07-03/5b3acc36c3539.jpg","超级可爱的小甜心","34"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-06-26/5b31e07b83181.jpg","减肥瘦身蔬菜沙拉","754"));
        list.add(new MuneBean("https://www.fancai.com//Uploads/MeiShi/2018-06-20/5b29bc8910c34.jpg","夏季美味冰淇淋夏季美味冰淇淋夏季美味冰淇淋","67"));


        // 加载适配器
        gridView.setAdapter(new MyAdapter(this));

        // 绑定监听事件（跳转到上传页面）
        imAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MuneActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });

        // 绑定监听事件（跳转到搜索页面）
        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MuneActivity.this,SerchActivity.class);
                startActivity(intent);
            }
        });
        evSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MuneActivity.this,SerchActivity.class);
                startActivity(intent);
            }
        });

        // 绑定监听事件（清楚搜索数据）
        imClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evSearch.setText("");
            }
        });
    }

    /**
     * 食谱的适配器
     */
    private class MyAdapter extends BaseAdapter {

        private Context mContext;

        public MyAdapter(Context context) {
            this.mContext = context;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            //绑定列表的ui
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mune, parent, false);
                viewHolder = new ViewHolder();
                //绑定试图id
                viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.number = (TextView) convertView.findViewById(R.id.tv_number);
                viewHolder.info = (TextView) convertView.findViewById(R.id.tv_info);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.info.setText(list.get(position).getInfo()); // 去食谱的介绍数据
            viewHolder.number.setText(list.get(position).getNumber()); // 去食谱的收藏数据

            // 这里加载网络图片，使用Glide这样的图片加载框架来异步加载图片 可参考https://blog.csdn.net/bzlj2912009596/article/details/81702367
            Glide.with(MuneActivity.this).load(list.get(position).getImgUrl()).placeholder(R.mipmap.ic_login_icon).into(viewHolder.img);

            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 点击列表跳转到其他页面
                    Intent intent =new Intent(MuneActivity.this,CheckActivity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }


        class ViewHolder {
            ImageView img;
            TextView number,info;
        }
    }
}
