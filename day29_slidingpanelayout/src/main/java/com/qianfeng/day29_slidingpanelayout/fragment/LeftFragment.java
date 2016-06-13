package com.qianfeng.day29_slidingpanelayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qianfeng.day29_slidingpanelayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment{

    private List<String> list;
    private CallBack callBack;
    private String[] urls;

    public LeftFragment(){
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof CallBack){
            callBack = (CallBack) context;
        }else{
            throw new RuntimeException(context.getClass() + "必须实现CallBack");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.add("千锋");
        list.add("搜狐");
        list.add("百度");
        list.add("腾讯");
        urls = new String[]{"http://www.1000phone.com", "http://www.sohu.com", "http://www.baidu.com", "http://www.qq.com",};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_left, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        ListView listView = (ListView) view;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                callBack.call(urls[position]);
               /* RightFragment rightFragment = (RightFragment) getFragmentManager().findFragmentById(R.id.right);
                rightFragment.loadUrl(urls[position]);*/
            }
        });
    }

    public interface CallBack{
        void call(String url);
    }
}
