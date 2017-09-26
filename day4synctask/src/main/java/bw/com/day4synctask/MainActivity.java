package bw.com.day4synctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    String result="索";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
    }
    public void click(View v){
        new AsyncTask<String,Void,String>(){
            //运行在主线程的
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            //运行在子线程里面
            @Override
            protected String doInBackground(String... params) {
                return params[0];
            }
       //运行在主线程
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                tv.setText(s);
            }
        }.execute(result);
    }
}
