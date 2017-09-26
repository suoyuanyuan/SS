package com.bw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.adapter.HomeVpAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bw.com.donghuacheng.ComicInfoActivity;
import bw.com.donghuacheng.R;

/**
 * Created by 索园 on 2017/8/18.
 */
public class HomeFragment extends Fragment {

    private View view;
    private GridView gv;
    private ViewPager vp;
    private LinearLayout ll;
    private int[] ids;
    private int current=4*10000;
    private Timer timer;
    private List<ImageView> imgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frangment_home, null);
        //获取控件
        findView();
        //为viewpager设置适配器
        vp.setAdapter(new HomeVpAdapter(getActivity(),ids));
        //为vp添加圆点
        addShape();
        //开启Timer实现自动轮播
        startTimer();
        //为控件设置监听
        setListener();
        return view;
    }

    private void setListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ComicInfoActivity.class);
                startActivity(intent);
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <imgs.size() ; i++) {
                    if(i==position%imgs.size()){
                        imgs.get(i).setImageResource(R.drawable.dot_focuse);
                    }else {
                        imgs.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
                if(current!=position){
                    timer.cancel();
                    timer=null;
                    current=position;
                    startTimer();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vp.setCurrentItem(current);
                        current++;
                    }
                });
            }
        },0,2000);
    }

    @Override
    public void onDestroy() {
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        super.onDestroy();
    }
    //添加小圆点
    private void addShape() {
        imgs = new ArrayList<ImageView>();
        for (int i = 0; i <ids.length ; i++) {
            ImageView img=new ImageView((getActivity()));
            img.setImageResource(R.drawable.dot_normal);
            imgs.add(img);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
            params.setMargins(5,5,5,5);
            ll.addView(img,params);
        }
        imgs.get(current% imgs.size()).setImageResource(R.drawable.dot_focuse);
    }

    private void findView() {
        gv = (GridView) view.findViewById(R.id.gv);
        vp = (ViewPager) view.findViewById(R.id.vp);
        ll =  (LinearLayout) view.findViewById(R.id.ll);
    }
}
