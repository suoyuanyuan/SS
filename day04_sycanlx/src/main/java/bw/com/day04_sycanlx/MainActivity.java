package bw.com.day04_sycanlx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private ImageView iv;
    private String path="http://169.254.116.183:8080/q.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.pb);
        iv = (ImageView) findViewById(R.id.iv);
    }
    public void click(View v){
        new AsyncTask<String, Void, Bitmap>() {
            private Bitmap bitmap;
            private HttpURLConnection connection;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL url=new URL(params[0]);
                    HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
                    //请求方式
                    connection.setRequestMethod("GET");
                    //请求超时时间
                    connection.setConnectTimeout(3000);
                    //请求响应码
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        //得到服务器返回的流
                        InputStream is = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return bitmap;

            }

            @Override
            protected void onPostExecute(Bitmap Bitmap) {
                super.onPostExecute(Bitmap);
                iv.setImageBitmap(Bitmap);
                pb.setVisibility(View.INVISIBLE);

            }
        }.execute(path);
    }
}
