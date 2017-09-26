package bw.com.donghuacheng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("flag",false)){
            initData();
            vp.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return list.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view==object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView(list.get(position));
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    container.addView(list.get(position));
                    return list.get(position);
                }
            });
        }else{
            assert vp != null;
            vp.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return 1;
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view==object;
                }
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    View view = View.inflate(NavigationActivity.this, R.layout.home_vp_item, null);
                    ImageView img = (ImageView) view.findViewById(R.id.img);
                    img.setImageResource(R.mipmap.dc);
                    container.addView(view);
                    return view;
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(NavigationActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);
        }

    }
    private void initData(){
        list = new ArrayList<View>();
       for(int num:MyData.NavigationVpid){
           View view = View.inflate(this, R.layout.home_vp_item, null);
           ImageView img = (ImageView) view.findViewById(R.id.img);
           img.setImageResource(num);
           list.add(view);
       }
        View view = list.get(list.size() - 1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("flag",true);
                edit.commit();
                Intent intent=new Intent(NavigationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}