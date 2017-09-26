package bw.com.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
String path="http://169.254.116.183:8080/dd.json";
    private GridView gv;
    private ByteArrayOutputStream baos;
    private List<DataBean.DataList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从服务器里获得数据

        String data = getData();
        Gson gson=new Gson();
        //得到解析对象
        DataBean dataBean = gson.fromJson(data, DataBean.class);
        //得到所有数据
        list = dataBean.datalist;


        gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(new MyAdapter());
    }

    private String getData() {
        new Thread(){


            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    baos=  new ByteArrayOutputStream();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        int len=-1;
                        byte[] buffer=new byte[1024];
                        while((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return baos.toString();
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = View.inflate(MainActivity.this, R.layout.tem, null);
            }
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            //把图片的地址转换成bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).course_pic);
            iv.setImageBitmap(bitmap);
            tv_name.setText(list.get(position).course_tname);
            tv_price.setText(list.get(position).course_price);

            return null;
        }
    };
}
