package com.qianfeng.day29;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private PullToRefreshListView ptfl;
    private List<String> data;
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
        for(int i = 0; i < 30; i++){
            data.add("与志玲的聊天记录" + i);
        }
    }

    private void initView(){
        ptfl = (PullToRefreshListView) findViewById(R.id.ptfl);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        ptfl.setAdapter(adapter);


        ptfl.setMode(PullToRefreshBase.Mode.BOTH); //设置即支持下拉，也支持上拉
        ptfl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            //下拉的时候回调
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
                Toast.makeText(MainActivity.this, "下拉", Toast.LENGTH_SHORT).show();
                new MyTask().execute();
            }
            //上拉的时候回调
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                Toast.makeText(MainActivity.this, "上拉", Toast.LENGTH_SHORT).show();
                new MyTask1().execute();
            }
        });



        /*ptfl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>(){
            //当用户下拉的时候，回调这个方法
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView){
                //用户下拉之后做的事情，都可以写在这里。 这里的代码是在主线程中执行的，所以不能在这里执行耗时操作
                new MyTask().execute();

            }
        });*/
        ILoadingLayout proxy = ptfl.getLoadingLayoutProxy();
        proxy.setPullLabel("aaa"); //设置正在拉的时候的提升
        proxy.setRefreshingLabel("正在刷新数据  bbb");
        proxy.setReleaseLabel("cccc");  //释放前的显示
//        proxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        String s = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        proxy.setLastUpdatedLabel(s);  //最后更新时间




    }


    class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params){
            SystemClock.sleep(3000); //模拟去网络下载数据的用时间
            for(int i = 0; i < 5; i++){

                data.add(0, "与志玲的最新的聊天记录" + i);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            adapter.notifyDataSetChanged();
            ptfl.onRefreshComplete(); //设置刷新视图不显示。这个方法必须是在主线程中调用，否则会抛异常
        }
    }
    class MyTask1 extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params){
            SystemClock.sleep(3000); //模拟去网络下载数据的用时间
            for(int i = 0; i < 5; i++){

                data.add( "与志玲的最新的  上拉  出来的聊天记录" + i);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            adapter.notifyDataSetChanged();
            ptfl.onRefreshComplete(); //设置刷新视图不显示。这个方法必须是在主线程中调用，否则会抛异常
        }
    }
}
