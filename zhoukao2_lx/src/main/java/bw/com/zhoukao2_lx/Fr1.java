package bw.com.zhoukao2_lx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 索园 on 2017/8/13.
 */
public class Fr1 extends Fragment {

    private ListView lv;
    String bb="http://www.meirixue.com/api.php?c=index&a=index";
    private List<Bean.DataBean.HotcategoryBean> hotcategory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, container, false);
        lv = (ListView) view.findViewById(R.id.lv);
        new Thread(){
            private ByteArrayOutputStream baos;

            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(bb);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(3000);
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        baos= new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int len=0;
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                       Data();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void Data() {
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(baos.toString(), Bean.class);
                        hotcategory =  bean.getData().getHotcategory();
                        lv.setAdapter(new MyAdapter());
                    }
                });
            }
        }.start();

        return view;
    }
/*    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv=new TextView(getActivity());
        tv.setText("页面一");
        return tv;
    }*/
    class MyAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return hotcategory.size();
    }

    @Override
    public Object getItem(int position) {
        return hotcategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= View.inflate(getActivity(), R.layout.it, null);
        }
        TextView t1 = (TextView) convertView.findViewById(R.id.t1);
        TextView t2 = (TextView) convertView.findViewById(R.id.t2);
        t1.setText(hotcategory.get(position).getCname());
        t2.setText(hotcategory.get(position).getCid());
        return convertView;
    }
}
}
