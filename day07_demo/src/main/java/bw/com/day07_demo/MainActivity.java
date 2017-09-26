package bw.com.day07_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ByteArrayOutputStream baos;
    private Button bt;
    private List<Bean.DataBean.AdlistBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String path="http://www.meirixue.com/api.php?c=index&a=index";
                        try {
                            URL url=new URL(path);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(3000);
                            connection.setReadTimeout(3000);
                            int responseCode = connection.getResponseCode();
                            baos = new ByteArrayOutputStream();
                            if(responseCode==200){
                                InputStream is = connection.getInputStream();
                                int len=-1;
                                byte[] buffer=new byte[1024];
                                while ((len=is.read(buffer))!=-1){
                                    baos.write(buffer,0,len);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String str = baos.toString();
                                    Gson gson=new Gson();
                                    Bean bean = gson.fromJson(str, Bean.class);
                                    list = bean.getData().getAdlist();
                                MyAdapter adapter=new MyAdapter(list,MainActivity.this);
                                lv.setAdapter(adapter);
                            }
                        });
                    }
                }.start();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean.DataBean.AdlistBean ba = (Bean.DataBean.AdlistBean) parent.getItemAtPosition(position);
                String s = ba.getImg();
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("img",s);
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                MyAdapter adapter=new MyAdapter(list,MainActivity.this);
                lv.setAdapter(adapter);
                return false;
            }
        });
    }

}
