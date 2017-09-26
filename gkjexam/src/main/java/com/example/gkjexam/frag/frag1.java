package com.example.gkjexam.frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gkjexam.MyBean.MyAdapter;
import com.example.gkjexam.MyBean.MyGsonBean;
import com.example.gkjexam.R;
import com.example.gkjexam.vpfragment.fragment1;
import com.example.gkjexam.vpfragment.fragment2;
import com.example.gkjexam.vpfragment.fragment3;
import com.example.gkjexam.vpfragment.fragment4;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭康杰 on 2017/8/17.
 */
public class frag1 extends Fragment {
    private ViewPager vp;
    private LinearLayout ll;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ArrayList<ImageView> imageViews;
    private ImageView imageView;
    private GridView gv;
    private List<String> result;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int m = msg.what;
            switch (m) {
                case 0:
                    int currentItem = vp.getCurrentItem();
                    currentItem++;
                    vp.setCurrentItem(currentItem);
                    sendadd();
                    break;
                case 1:
                    Gson gson = new Gson();
                    MyGsonBean myGsonBean = gson.fromJson(byteArrayOutputStream.toString(), MyGsonBean.class);
                    result = myGsonBean.getResult();
                    MyAdapter myAdapter = new MyAdapter(result, getActivity());
                    gv.setAdapter(myAdapter);
                    break;
            }

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        vp = (ViewPager) view.findViewById(R.id.vp);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        gv = (GridView) view.findViewById(R.id.gv);
        gridview();
        getviewpager();
        sendadd();
        getll();
        return view;
    }

    public void gridview() {
        new Thread() {

            @Override
            public void run() {
                super.run();
                String path = "http://japi.juhe.cn/comic/category?key=9795a9103900a0441b39fee5e22269d3";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        int len = -1;
                        byte[] b = new byte[1024];
                        while ((len = inputStream.read(b)) != -1) {
                            byteArrayOutputStream.write(b, 0, len);
                        }
                    }

                    handler.sendEmptyMessage(1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void getviewpager() {
        vp.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position % 4) {
                    case 0:
                        fragment = new fragment1();
                        break;
                    case 1:
                        fragment = new fragment2();
                        break;
                    case 2:
                        fragment = new fragment3();
                        break;
                    case 3:
                        fragment = new fragment4();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 100000;
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int x = 0; x < 4; x++) {
                    if (position % 4 == x) {
                        imageViews.get(position % 4).setImageResource(R.drawable.layout1);
                    } else {
                        imageViews.get(x).setImageResource(R.drawable.layout2);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void sendadd() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }


    public void getll() {
        imageViews = new ArrayList<>();
        if (imageViews != null) {
            imageViews.clear();
        }
        for (int x = 0; x < 4; x++) {
            imageView = new ImageView(getActivity());
            if (x == 0) {
                imageView.setImageResource(R.drawable.layout1);
            } else {
                imageView.setImageResource(R.drawable.layout2);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(10, 0, 10, 0);
            ll.addView(imageView, layoutParams);
            imageViews.add(imageView);
        }
    }
}
