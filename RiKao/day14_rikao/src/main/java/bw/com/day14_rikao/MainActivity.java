package bw.com.day14_rikao;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void OnClickListener() {
        Toast.makeText(MainActivity.this,"OnClickListener走了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initFindView() {
        Toast.makeText(MainActivity.this,"initFindView走了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }
}
