package com.example.gkjexam.frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gkjexam.MyBean.MyCariAdapter;
import com.example.gkjexam.MyBean.MyCariBean;
import com.example.gkjexam.MyBean.Mycaricature;
import com.example.gkjexam.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭康杰 on 2017/8/17.
 */
public class frag2 extends Fragment {

    private EditText et_name;
    private ImageView ib_select;
    private ListView lv_show;
    private ByteArrayOutputStream byteArrayOutputStream;
    private Handler handler = new Handler() {

        private MyCariBean myCariBean;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int m = msg.what;
            switch (m) {
                case 0:
                    Gson gson = new Gson();
                    Mycaricature mycaricature = gson.fromJson(byteArrayOutputStream.toString(), Mycaricature.class);
                    List<Mycaricature.ResultBean.BookListBean> bookList = mycaricature.getResult().getBookList();
                    for (Mycaricature.ResultBean.BookListBean arr : bookList) {
                        String area = arr.getArea();
                        String coverImg = arr.getCoverImg();
                        int lastUpdate = arr.getLastUpdate();
                        String name = arr.getName();
                        String type = arr.getType();
                        MyCariBean myCariBean = new MyCariBean(area, coverImg, lastUpdate, name, type);
                        myCarilist.add(myCariBean);
                    }
                    MyCariAdapter myCariAdapter = new MyCariAdapter(myCarilist, getActivity());
                    lv_show.setAdapter(myCariAdapter);
                    break;
                case 1:
                    Gson gson1 = new Gson();
                    Mycaricature mycaricature1 = gson1.fromJson(byteArrayOutputStream.toString(), Mycaricature.class);
                    List<Mycaricature.ResultBean.BookListBean> bookList1 = mycaricature1.getResult().getBookList();
                    for (Mycaricature.ResultBean.BookListBean arr : bookList1) {
                        String area = arr.getArea();
                        String coverImg = arr.getCoverImg();
                        int lastUpdate = arr.getLastUpdate();
                        String name = arr.getName();
                        String type = arr.getType();
                       // int i = names.indexOf(name,0);
                        Toast.makeText(getActivity(), "已經進入"+name+names, Toast.LENGTH_SHORT).show();
                       // if (i>=1){
                           // Toast.makeText(getActivity(), "已經進入", Toast.LENGTH_SHORT).show();
                           // MyCariBean   myCariBean =  new MyCariBean(area, coverImg, lastUpdate, name, type);
                       // }
                    }
                   // myCarilist.add(myCariBean);
                    break;
            }

        }
    };


    private ArrayList<MyCariBean> myCarilist;
    private String names;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        myCarilist = new ArrayList<>();
        et_name = (EditText) view.findViewById(R.id.et_name);
        ib_select = (ImageView) view.findViewById(R.id.ib_select);
        lv_show = (ListView) view.findViewById(R.id.lv_show);
        gridview();
        colick();
        return view;
    }

    public void colick() {
        ib_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names = et_name.getText().toString();
                if(TextUtils.isEmpty(names)){
                    Toast.makeText(getActivity(), "搜索不能為空！", Toast.LENGTH_SHORT).show();
                }else{
                    handler.sendEmptyMessage(1);
                }


            }
        });
    }

    public void gridview() {
        new Thread() {

            @Override
            public void run() {
                super.run();
                String path = "http://japi.juhe.cn/comic/book?key=9795a9103900a0441b39fee5e22269d3";
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

                    handler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
