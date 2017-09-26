package bw.com.day13_baseactivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
    }

    @Override
    public void initFindView() {

        Toast.makeText(MainActivity.this,"initFindView走了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"initView",Toast.LENGTH_SHORT).show();
    }
}
