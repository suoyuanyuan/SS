package bw.com.yuekaolx2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ByteArrayOutputStream baos;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        new Thread(){

            @Override
            public void run() {
                super.run();
                String path="http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1457659690&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";

                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(3000);
                    connection.setConnectTimeout(3000);
                    connection.setDoInput(true);
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        baos = new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int len;
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        runOnUiThread(new Runnable() {

                            private List<Bean.DataBean> list;

                            @Override
                            public void run() {
                                Gson gson=new Gson();
                                Bean bean = gson.fromJson(baos.toString(), Bean.class);
                                list = bean.getData();
                                MyAdapter adapter=new MyAdapter(list,MainActivity.this);
                                lv.setAdapter(adapter);

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
