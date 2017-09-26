package bw.com.suoyuanyuan20170814;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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

import bw.com.suoyuanyuan20170814.com.bw.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    String path="http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603";
    private List<Bean.ResultBean.RowsBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout dl = (DrawerLayout)findViewById(R.id.dl);
        ListView lv = (ListView) findViewById(R.id.lv);
        XListView xlv = (XListView) findViewById(R.id.xlv);
        xlv.setXListViewListener(this);
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
        new Thread(){
            private ByteArrayOutputStream baos;

            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        baos= new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int len;
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Gson gson=new Gson();
                                Bean bean = gson.fromJson(baos.toString(), Bean.class);
                                list = bean.getResult().getRows();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
            }
            TextView t1 = (TextView) convertView.findViewById(R.id.t1);
            TextView t2 = (TextView) convertView.findViewById(R.id.t2);
           // ImageLoader.getInstance().displayImage(list.get(position).getInfo().);
            t1.setText(list.get(position).getFang_type());
            t2.setText(list.get(position).getFang_type());
            return convertView;
        }
    }
}
