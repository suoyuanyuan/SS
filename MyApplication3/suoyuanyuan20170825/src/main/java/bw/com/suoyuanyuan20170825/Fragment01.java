package bw.com.suoyuanyuan20170825;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import bw.com.suoyuanyuan20170825.utils.XListView;

/**
 * Created by 索园 on 2017/8/25.
 */

public class Fragment01 extends Fragment {
    String path="http://www.meirixue.com/api.php";
    String data="?c=index&a=index";
    private List<Bean.DataBean.HotcategoryBean> hotcategory;
    private List<Bean.DataBean.AdlistBean> adlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ragment01, container, false);
        final XListView xlv = (XListView) view.findViewById(R.id.xlv);
        new AsyncTask<String, Void, String>() {


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
                hotcategory = bean.getData().getHotcategory();
                View view1 = View.inflate(getActivity(), R.layout.item, null);
                xlv.addHeaderView(view1);
                ViewPager vp2 = (ViewPager) view1.findViewById(R.id.vp2);
                MyPager pager=new MyPager();
                vp2.setAdapter(pager);

            }
        }.execute();
        return view;
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
        ImageView iv=new ImageView(getActivity());
        ImageLoader.getInstance().displayImage(adlist.get(position).getImg(),iv);
        container.addView(iv);
        return iv;
    }
}
}
