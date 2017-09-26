package bw.com.day13_dome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
    String[] title={"推荐","野史","后宫","解密"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabs.setViewPager(vp);
    }
    class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case 0:
                fragment=new Fragment01();
                    break;
                case 1:
                    fragment=new Fragment02();
                    break;
                case 2:
                    fragment=new Fragment03();
                    break;
                case 3:
                    fragment=new Fragment04();
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
