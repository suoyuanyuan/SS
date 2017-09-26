package com.example.gkjexam.Activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.gkjexam.MainActivity;
import com.example.gkjexam.R;
import com.example.gkjexam.frag.frag1;
import com.example.gkjexam.frag.frag2;
import com.example.gkjexam.frag.frag3;


import java.util.ArrayList;

public class SuccessActivity extends AppCompatActivity {


    private ViewPager vp1;
    private ImageView shutdown;
    private ImageView iv_one;
    private ImageView iv_two;
    private ImageView iv_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        vp1 = (ViewPager) findViewById(R.id.vp1);
        shutdown = (ImageView) findViewById(R.id.shutdown);
        iv_one = (ImageView) findViewById(R.id.iv_one);
        iv_two = (ImageView) findViewById(R.id.iv_two);
        iv_three = (ImageView) findViewById(R.id.iv_three);
        getviewpager();
        getclick();
    }

    public void getclick() {
        iv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_one.setImageResource(R.mipmap.home_select);
                iv_two.setImageResource(R.mipmap.search_normal);
                iv_three.setImageResource(R.mipmap.collect_normal);
                vp1.setCurrentItem(0);
            }
        });
        iv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_one.setImageResource(R.mipmap.home_normal);
                iv_two.setImageResource(R.mipmap.search_select);
                iv_three.setImageResource(R.mipmap.collect_normal);
                vp1.setCurrentItem(1);
            }
        });
        iv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_one.setImageResource(R.mipmap.home_normal);
                iv_two.setImageResource(R.mipmap.search_normal);
                iv_three.setImageResource(R.mipmap.collect_select);
                vp1.setCurrentItem(2);
            }
        });


        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuccessActivity.this.finish();
            }
        });
    }

    public void getviewpager() {
        vp1.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new frag1();
                        break;
                    case 1:
                        fragment = new frag2();
                        break;
                    case 2:
                        fragment = new frag3();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        vp1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        iv_one.setImageResource(R.mipmap.home_select);
                        iv_two.setImageResource(R.mipmap.search_normal);
                        iv_three.setImageResource(R.mipmap.collect_normal);
                        break;
                    case 1:
                        iv_one.setImageResource(R.mipmap.home_normal);
                        iv_two.setImageResource(R.mipmap.search_select);
                        iv_three.setImageResource(R.mipmap.collect_normal);
                        break;
                    case 2:
                        iv_one.setImageResource(R.mipmap.home_normal);
                        iv_two.setImageResource(R.mipmap.search_normal);
                        iv_three.setImageResource(R.mipmap.collect_select);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
