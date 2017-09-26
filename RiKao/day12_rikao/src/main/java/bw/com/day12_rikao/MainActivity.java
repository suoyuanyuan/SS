package bw.com.day12_rikao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
String[] title={"头条","社会","国内","娱乐"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPageAdapter(getSupportFragmentManager()));

    }
    class MyPageAdapter extends FragmentPagerAdapter{
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment3 fragment=null;
            switch (position){
                case 0:
                    fragment=new bw.com.day12_rikao.Fragment();
                    break;
                case 1:
                    fragment= new bw.com.day12_rikao.Fragment();
                    break;
                case 2:
                    fragment= new bw.com.day12_rikao.Fragment3();
                    break;
                case 3:
                    fragment=new bw.com.day12_rikao.Fragment4();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
