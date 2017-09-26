package bw.com.donghuacheng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.adapter.MyFragmentPagerAdapter;
import com.bw.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int current=0;
    private ViewPager vp;
    private DrawerLayout dl;
    private ImageView img_home;
    private List<Fragment> list;
    private List<ImageView> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置控件
        findview();
        //设置viewpager数据
        initData();
        // 为viewpager设置适配器
       vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),list));
        //设置监听
      //  setListener();


    }

    private void setListener() {
        img_home.setOnClickListener(this);
    }

    private void initData() {
        list = new ArrayList<>();
        imgs = new ArrayList<>();
        HomeFragment main=new HomeFragment();
        list.add(main);
        // 将ImageView添加到集合
        imgs.add(img_home);
    }

    private void findview() {
        //主业布局
        dl = (DrawerLayout) findViewById(R.id.dl);
        vp = (ViewPager) findViewById(R.id.vp);
        img_home = (ImageView) findViewById(R.id.img_home);
        ImageView img_search = (ImageView) findViewById(R.id.img_search);
        ImageView img_collect = (ImageView) findViewById(R.id.img_collect);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_home:
                vp.setCurrentItem(0);
                imgs.get(current).setImageResource(MyData.normalId[current]);
                imgs.get(0).setImageResource(MyData.selectId[0]);
                current=0;
                break;
        }
    }
}
