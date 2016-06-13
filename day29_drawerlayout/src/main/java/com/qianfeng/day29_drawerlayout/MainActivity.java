package com.qianfeng.day29_drawerlayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    private ListView drawer;
    String[] urls = new String[]{"http://www.1000phone.com", "http://www.sohu.com", "http://www.baidu.com", "http://www.qq.com",};
    private RightFragment rightFragment;
    private FragmentManager manager;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container, new RightFragment(), "right").commit();
        initView();
    }

    private void initView(){
        drawer = (ListView) findViewById(R.id.drawer);
        drawer.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                rightFragment = (RightFragment) manager.findFragmentByTag("right");
                Log.d("MainActivity", "rightFragment:" + rightFragment);
                rightFragment.loadUrl(urls[position]);
                drawerLayout.closeDrawers(); //关闭所有的drawer
//                drawerLayout.closeDrawer(Gravity.LEFT);  //关闭指定方法的

            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //设置抽屉监听器
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener(){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset){
                Log.d("MainActivity", "slideOffset:" + slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView){
                Log.d("MainActivity", "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView){
                Log.d("MainActivity", "onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState){
                switch(newState){
                    case DrawerLayout.STATE_DRAGGING:
                        Log.d("MainActivity", "STATE_DRAGGING");
                        break;
                    case DrawerLayout.STATE_IDLE:
                        Log.d("MainActivity", "STATE_IDLE");
                        break;
                    case DrawerLayout.STATE_SETTLING:
                        Log.d("MainActivity", "STATE_SETTLING");
                        break;
                }
            }
        });
    }
}
