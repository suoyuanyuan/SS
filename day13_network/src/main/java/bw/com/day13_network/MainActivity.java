package bw.com.day13_network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkutils();

    }

    private void NetWorkutils() {
        //得到所有连接的管理器
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到所有的连接的信息
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            Toast.makeText(this,"网络已连接",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"网络没有连接",Toast.LENGTH_SHORT).show();
        }
    }
}
