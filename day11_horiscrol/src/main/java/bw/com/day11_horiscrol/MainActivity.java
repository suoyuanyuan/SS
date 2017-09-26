package bw.com.day11_horiscrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
String[] title={"首页","推荐","新闻","视频","娱乐"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerSlidingTabStrip tabs= (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(vp);
    }
    class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragmet=null;
            switch (position){
                case 0:
                    fragmet=new Fragmet();
                    break;
                case 1:
                    fragmet=new Fragmet2();
                    break;
                case 2:
                    fragmet=new Fragmet3();
                    break;
                case 3:
                    fragmet=new Fragmet4();
                    break;
                case 4:
                    fragmet=new Fragmet5();
                    break;
            }
            return fragmet;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
