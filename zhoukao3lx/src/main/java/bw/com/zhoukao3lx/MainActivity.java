package bw.com.zhoukao3lx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.List;

import bw.com.zhoukao3lx.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    String path="http://www.meirixue.com/api.php";
    String data = "?c=index&a=index";
    private ViewPager vp;
    private MyPager pager;
    private List<Bean.DataBean.AdlistBean> adlist;
    private List<Bean.DataBean.HotcategoryBean> hotcategory;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num = msg.what;
            switch (num){
                case 1:
                    int currentItem = vp.getCurrentItem();
                    currentItem++;
                    vp.setCurrentItem(currentItem);
                    aa();
                    break;
                case 2:
                    pager.notifyDataSetChanged();
                    close();
                    break;

            }
        }
    };
    private XListView xlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView) findViewById(R.id.xlv);
        new AsyncTask<String,Void,String>(){


            private ByteArrayOutputStream baos;
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
                    baos= new ByteArrayOutputStream();
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        byte[] b=new byte[1024];
                        int len;
                        while ((len=inputStream.read(b))!=-1){
                            baos.write(b,0,len);
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
                hotcategory = bean.getData().getHotcategory();
                //////////////////////////
                View view = View.inflate(MainActivity.this, R.layout.item, null);
                xlv.addHeaderView(view);
                vp = (ViewPager) view.findViewById(R.id.vp);
                LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
                pager = new MyPager();
                vp.setAdapter(pager);
                aa();
                xllist();
                xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            new Thread().sleep(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        }.execute(path);
    }
    public void close(){
        //停止加载更多
        xlv.stopLoadMore();
        //停止刷新
        xlv.stopRefresh();

        xlv.setRefreshTime("2017:8:10");
    }
    private void xllist() {
        MyAdapter myadapetr=new MyAdapter();
        xlv.setAdapter(myadapetr);
        xlv.setXListViewListener(this);
        close();
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

    @Override
    public void onLoadMore() {

    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return adlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=  View.inflate(MainActivity.this,R.layout.item2,null);
            }
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            //展示图片
            ImageLoader.getInstance().displayImage(hotcategory.get(position).getImg(),iv);
            //文字
            tv.setText(hotcategory.get(position).getCname());
           /* TextView tv=new TextView(MainActivity.this);
            tv.setText(adlist.get(position).getTitle());*/
            return convertView;
        }
    }
    private void aa() {
        handler.sendEmptyMessageDelayed(1,2000);
    }
    class MyPager extends PagerAdapter{
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
            ImageView imageview=new ImageView(MainActivity.this);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(adlist.get(position%adlist.size()).getImg(),imageview);
            container.addView(imageview);
            return imageview;
        }
    }
}
