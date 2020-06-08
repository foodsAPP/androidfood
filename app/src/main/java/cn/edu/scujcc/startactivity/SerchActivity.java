package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SerchActivity extends AppCompatActivity {
    private ListView listView;      //列表
    private SearchView searchView;  //搜索框

    private List<String> data = new ArrayList<>();  //ListView数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);

//        //设置延时搜索，延时2秒
//        searchView.setWaitTime(2000);

        //设置搜索方法
        searchView.setSearchWay(new SearchView.SearchWay<String>(){

            @Override
            public List<String> getData() {
                //返回数据源
                return data;
            }

            @Override
            public boolean matchItem(String item, String s) {
                //如果串item中包含串s，则匹配
                return item.contains(s);
            }

            @Override
            public void update(List<String> resultList) {
                //更新ListView的数据
                setListViewData(resultList);
            }
        });

        initListView();
    }

    /**
     * 初始化ListView
     */
    private void initListView(){
        //初始化字符数组
        for (int i = 0; i < 20; i++){
            data.add("第" + i + "项");
        }

        //初始化ListView的内容
        setListViewData(data);
    }

    /**
     * 设置ListView的内容
     */
    private void setListViewData(List<String> list){
        //设置ListView的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}