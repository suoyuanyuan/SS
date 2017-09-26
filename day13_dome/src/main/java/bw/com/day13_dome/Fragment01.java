package bw.com.day13_dome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 索园 on 2017/8/16.
 */
public class Fragment01 extends Fragment {
    String path="http://169.254.116.183:8080/aa.json";
    private List<Bean.ArrayBean> list=new ArrayList<>();
    private String str;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lv = new ListView(getActivity());
        if(list!=null){
            list.clear();
        }
        new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(3000);
                    connection.setDoInput(true);
                    int responseCode = connection.getResponseCode();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        int len=0;
                        byte[] buffer=new byte[1024];
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        str =   baos.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(str, Bean.class);
                        for (int i = 0; i <bean.getArray().size() ; i++) {
                            if(bean.getArray().get(i).getType().equals("推荐")){
                                list.add(bean.getArray().get(i));
                            }
                        }
                        MyAdapter adapter=new MyAdapter(list,getActivity());
                        lv.setAdapter(adapter);
                    }
                });
            }
        }.start();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean.ArrayBean ab = (Bean.ArrayBean) parent.getItemAtPosition(position);
                Intent intent=new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("ab",ab);
                startActivity(intent);
            }
        });
        return lv;
    }
}
