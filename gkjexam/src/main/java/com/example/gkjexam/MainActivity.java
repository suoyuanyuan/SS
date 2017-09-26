package com.example.gkjexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gkjexam.Activity.SuccessActivity;
import com.example.gkjexam.listfrag.listfrag1;
import com.example.gkjexam.listfrag.listfrag2;
import com.example.gkjexam.listfrag.listfrag3;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences gkjexam = getSharedPreferences("gkjexam", MODE_PRIVATE);
        boolean judge = gkjexam.getBoolean("judge", false);
        if (judge){
            Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
            startActivity(intent);
            finish();
        }
        vp = (ViewPager) findViewById(R.id.vp);
     vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
         @Override
         public Fragment getItem(int position) {
             Fragment fragment = null;
             switch (position){
                 case 0:
                     fragment = new listfrag1();
                     break;
                 case 1:
                     fragment = new listfrag2();
                     break;
                 case 2:
                     fragment = new listfrag3();
                     break;
             }
             return fragment;
         }

         @Override
         public int getCount() {
             return 3;
         }
     });
    }
}
