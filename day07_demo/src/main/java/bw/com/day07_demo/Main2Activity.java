package bw.com.day07_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Main2Activity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        iv = (ImageView) findViewById(R.id.m_iv);
        Intent intent = getIntent();
        String s = intent.getStringExtra("img");
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Main2Activity.this));
        ImageLoader.getInstance().displayImage(s,iv);
    }
}
