package com.qianfeng.day29_swiperefreshlayout;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ListView listView;
    private List<String> data;
    private SwipeRefreshLayout wrfl;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData(){
        data = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            data.add("张三" + i);
        }
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        wrfl = (SwipeRefreshLayout) findViewById(R.id.srfl);
        wrfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                new MyTask().execute();
            }
        });
        wrfl.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK, Color.GREEN);
        wrfl.setSize(SwipeRefreshLayout.LARGE);
    }

    class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params){
            for(int i = 0; i < 5; i++){
                data.add(0, "李四" + i);
            }
            SystemClock.sleep(3000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            adapter.notifyDataSetChanged();
            wrfl.setRefreshing(false);
        }
    }


}
