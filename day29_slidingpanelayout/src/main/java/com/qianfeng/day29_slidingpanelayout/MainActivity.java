package com.qianfeng.day29_slidingpanelayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qianfeng.day29_slidingpanelayout.fragment.LeftFragment;
import com.qianfeng.day29_slidingpanelayout.fragment.RightFragment;

public class MainActivity extends AppCompatActivity implements LeftFragment.CallBack{

    private RightFragment rightFragment;
    private SlidingPaneLayout spl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        rightFragment = (RightFragment) manager.findFragmentById(R.id.right);
        spl = (SlidingPaneLayout) findViewById(R.id.spl);
//        spl.setShadowResourceLeft(R.mipmap.ic_launcher);
//        spl.setShadowResourceRight(R.mipmap.ic_launcher);
        
        //设置面板拉动的监听器
        spl.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener(){
            @Override
            public void onPanelSlide(View panel, float slideOffset){
                Log.d("MainActivity", "onPanelSlide..");
            }

            @Override
            public void onPanelOpened(View panel){
                Log.d("MainActivity", "onPanelOpened..");
                Toast.makeText(MainActivity.this, "开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelClosed(View panel){
                Log.d("MainActivity", "onPanelClosed..");
                Toast.makeText(MainActivity.this, "关了", Toast.LENGTH_SHORT).show();
            }
        });
//        rightFragment.loadUrl("http://www.1000phone.com");
    }

    @Override
    public void call(String url){
        //        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        rightFragment.loadUrl(url);
        spl.closePane();//把左面板关闭
    }
}
