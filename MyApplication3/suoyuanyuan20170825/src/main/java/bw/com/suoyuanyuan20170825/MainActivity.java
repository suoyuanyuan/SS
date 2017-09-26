package bw.com.suoyuanyuan20170825;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bw.com.suoyuanyuan20170825.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xlv;
    String path="http://www.meirixue.com/api.php";
    String data = "?c=index&a=index";
    private List<Bean.DataBean.HotcategoryBean> hotcategory;
    private List<Bean.DataBean.AdlistBean> adlist;
    private LinearLayout ll;
    private ViewPager vp2;
    private ByteArrayOutputStream baos;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num = msg.what;
            switch (num){
                case 1:
                    int currentItem = vp2.getCurrentItem();
                    currentItem++;
                    vp2.setCurrentItem(currentItem);
                    aa();
                    break;
                case 2:
                    pager.notifyDataSetChanged();
                    stop();
                    break;
            }
        }
    };
    private MyPager pager;
    private List<ImageView> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView)findViewById(R.id.xlv);
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url=new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(3000);
                    connection.setDoInput(true);
                    connection.getOutputStream().write(data.getBytes());
                    baos = new ByteArrayOutputStream();
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream is = connection.getInputStream();
                        byte[] buffer=new byte[1024];
                        int len;
                        while ((len=is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        return baos.toString();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson=new Gson();
                Bean bean = gson.fromJson(baos.toString(), Bean.class);
                adlist = bean.getData().getAdlist();
                View view = View.inflate(MainActivity.this, R.layout.item, null);
                xlv.addHeaderView(view);
                vp2 = (ViewPager) view.findViewById(R.id.vp2);
                ll = (LinearLayout) view.findViewById(R.id.ll);
                //实现轮播
                aa();
                pager = new MyPager();
                vp2.setAdapter(pager);
                //设置圆点
                bb();
                cc();
                xlist();
            }

        }.execute(path);

    }
    private void xlist() {
     MyAdapter adapte=new MyAdapter();
        xlv.setAdapter(adapte);
        xlv.setXListViewListener(this);
    }
//下拉刷新
    @Override
    public void onRefresh() {
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  adlist.add(0,adlist.get(1));
                  handler.sendEmptyMessage(2);
              }
          },2000);
    }
    //停止刷新
public void stop(){
    xlv.stopRefresh();
    xlv.stopLoadMore();
}
    //上拉加载
    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adlist.add(0,adlist.get(1));
                handler.sendEmptyMessage(2);
            }
        },2000);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return adlist.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv=new TextView(MainActivity.this);
            tv.setText(adlist.get(position).getTitle());
            return tv;
        }
    }
    private void aa() {
      handler.sendEmptyMessageDelayed(1,3000);
    }
    private void bb() {
        views = new ArrayList<>();
        if (views !=null){
            views.clear();
        }
        for (int i = 0; i <adlist.size() ; i++) {
          ImageView iv=new ImageView(MainActivity.this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.setMargins(10,0,10,0);
            ll.addView(iv,params);
            views.add(iv);
        }
    }
    private void cc() {
        vp2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i <views.size() ; i++) {
                    if(position%adlist.size()==i){
                         views.get(position%adlist.size()).setImageResource(R.drawable.yes);
                    }else {
                        views.get(i).setImageResource(R.drawable.no);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class MyPager extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
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
            ImageView iv=new ImageView(MainActivity.this);
            ImageLoader.getInstance().displayImage(adlist.get(position%adlist.size()).getImg(),iv);
            container.addView(iv);
            return iv;
        }
    }
}
