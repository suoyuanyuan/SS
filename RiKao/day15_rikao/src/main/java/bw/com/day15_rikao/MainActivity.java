package bw.com.day15_rikao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
String path="https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&amp;num=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<String,Void,Bitmap>(){

            private Bitmap bitmap;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //请求方式
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setDoInput(true);
                    connection.setReadTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
            }
        }.execute(path);
    }
}
