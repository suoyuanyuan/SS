package bw.com.day05_caipu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button bt;
    private TextView tv;
    private String str=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        bt =  (Button) findViewById(R.id.bt);
        tv =    (TextView) findViewById(R.id.tv);
        bt.setOnClickListener(new View.OnClickListener() {
            final String name = et.getText().toString();
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        super.run();

                        String path="http://apis.juhe.cn/cook/query?menu="+"红烧肉"+"&key=81ad3d842d63c6e7089b8fc174985bcb";

                        try {
                            URL url=new URL(path);
                            //得到HttpsURLConnection对象
                            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                            //请求方式
                            connection.setRequestMethod("GET");
                            //设置它的连接超时时间
                            connection.setConnectTimeout(3000);
                            connection.setReadTimeout(3000);
                            connection.setDoInput(true);
                            int responseCode = connection.getResponseCode();
                            ByteArrayOutputStream baos=new ByteArrayOutputStream();
                            if(responseCode==200){
                                InputStream is = connection.getInputStream();
                                byte[] buffer =new byte[1024];
                                int len=-1;
                                while ((len=is.read(buffer))!=-1){
                                    baos.write(buffer,0,len);
                                }
                                str = baos.toString();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
                                tv.setText(str);

                            }
                        });
                    }
                }.start();
            }
        });

    }


}
