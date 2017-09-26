package bw.com.day14_rikao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        initFindView();
        OnClickListener();
    }

    protected abstract void OnClickListener();

    public abstract void initFindView();

    public abstract void initView();
    
}
