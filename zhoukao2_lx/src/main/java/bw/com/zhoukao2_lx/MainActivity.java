package bw.com.zhoukao2_lx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> list;
    //private List<View> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
   /*     View view1 = LayoutInflater.from(this).inflate(R.layout.fragment01, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment02, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.fragment03, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        sharedPreferences.getBoolean("bt")
        if(){

        }*/

        list = new ArrayList<>();
        Fragemt01 f1=new Fragemt01();
        Fragemt02 f2=new Fragemt02();
        Fragemt03 f3=new Fragemt03();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
