package bw.com.day02_rikao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View v){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String path="http://169.254.116.183:8080/aa.json";
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        byte[] buffer=new byte[1024*5];
                        int len=0;
                        while((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        System.out.println(new String(baos.toByteArray()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
