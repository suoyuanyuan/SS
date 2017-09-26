package bw.com.yuekaolx;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import bw.com.yuekaolx.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
private String path="http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457695555&loc_mode=5&lac=4527&cid=28883&iid=3835029558&device_id=12211880440&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=1";
    private List<Bean.DataBean> list;
    private SQLiteDatabase sd;
    private Handler handler=new Handler(){
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    private XListView xlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView) findViewById(R.id.xlv);
        SQlite sqlite=new SQlite(MainActivity.this);
        sd = sqlite.getReadableDatabase();
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
                    baos = new ByteArrayOutputStream();
                    if (responseCode==200){
                        InputStream is = connection.getInputStream();
                        int len;
                        byte[] buffer=new byte[1024];
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Gson gson=new Gson();
                                Bean bean = gson.fromJson(baos.toString(), Bean.class);
                                list = bean.getData();
                                for (int i = 0; i <list.size() ; i++) {
                                    long id = list.get(i).getItem_id();
                                    String title = list.get(i).getTitle();
                                    String source = list.get(i).getSource();
                                    String article_url = list.get(i).getArticle_url();
                                    sd.execSQL("insert into news(item_id,title,source,article_url) values(?,?,?,?)",
                                            new String[]{id+"",title,source,article_url} );
                                }
                                xlv.setAdapter(new MyAdapter());
                                xlv.setXListViewListener(MainActivity.this);

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    //下拉刷新
    @Override
    public void onRefresh() {
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            URL url=new URL(path);
                            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(3000);
                            int responseCode = connection.getResponseCode();
                            final ByteArrayOutputStream baos=new ByteArrayOutputStream();
                            if(responseCode==200){
                                InputStream is = connection.getInputStream();
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
                                        list = bean.getData();
                                        list.addAll(list);
                                        close();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
             }
         },2000);
    }
    public void close(){
        xlv.stopLoadMore();
        xlv.stopRefresh();
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
                convertView=View.inflate(MainActivity.this,R.layout.item,null);
            }
            TextView tv1 = (TextView) convertView.findViewById(R.id.t1);
            TextView tv2 = (TextView) convertView.findViewById(R.id.t2);
            tv1.setText(list.get(position).getTitle());
            tv2.setText(list.get(position).getSource());
            return convertView;
        }
    }

}
