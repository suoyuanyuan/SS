package bw.com.zhoukao2_lx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    String aa="http://www.meirixue.com/api.php?c=index&a=index";
    private List<Bean.DataBean.AdlistBean> adlist;
    private ViewPager vp2;
    private PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vp2 = (ViewPager) findViewById(R.id.vp2);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        final ViewPager vp3 = (ViewPager) findViewById(R.id.vp3);

        new Thread(){
            private ByteArrayOutputStream baos;

            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(aa);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        baos= new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int len=0;
                        while ((len=inputStream.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        data();
                        data2();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void data2() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(baos.toString(), Bean.class);
                        adlist = bean.getData().getAdlist();
                        vp3.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                            @Override
                            public CharSequence getPageTitle(int position) {
                                return adlist.get(position).getTitle();
                            }

                            @Override
                            public Fragment getItem(int position) {
                                Fragment fragment=new Fragment();
                                switch (position){
                                    case 0:
                                    fragment=new Fr1();
                                         break;
                                    case 1:
                                        fragment=new Fr1();
                                        break;
                                    case 2:
                                        fragment=new Fr1();
                                        break;
                                    case 3:
                                        fragment=new Fr1();
                                        break;

                                }
                                return fragment;
                            }

                            @Override
                            public int getCount() {
                                return adlist.size();
                            }
                        });
                    }
                });
                tabs.setViewPager(vp3);
            }


            private void data() {
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(baos.toString(), Bean.class);
                        adlist =  bean.getData().getAdlist();
                        MyPage mypagr=new MyPage();
                        vp2.setAdapter(mypagr);
                    }
                });
            }
        }.start();
    }
    class MyPage extends PagerAdapter{
        @Override
        public int getCount() {
            return adlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=new ImageView(Main2Activity.this);
            ImageLoader.getInstance().displayImage(adlist.get(position).getImg(),iv);
            container.addView(iv);
            return iv;
        }
    }
}
