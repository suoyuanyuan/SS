package bw.com.day8_rikao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bean.NewslistBean> list;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt);
        lv = (ListView) findViewById(R.id.lv);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    private ByteArrayOutputStream baos;

                    @Override
                    public void run() {
                        super.run();
                        String path="https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
                        try {
                            URL url=new URL(path);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setReadTimeout(3000);
                            connection.setConnectTimeout(3000);
                            int responseCode = connection.getResponseCode();
                            baos= new ByteArrayOutputStream();
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String str = baos.toString();
                                Gson gson=new Gson();
                                Bean bean=gson.fromJson(str,Bean.class);
                                list =  bean.getNewslist();
                                MyAdapter adapter=new MyAdapter(list,MainActivity.this);
                                lv.setAdapter(adapter);
                            }
                        });
                    }
                }.start();
            }
        });

    }
}
