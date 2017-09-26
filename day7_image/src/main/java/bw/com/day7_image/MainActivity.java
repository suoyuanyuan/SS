package bw.com.day7_image;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity {
    String[] images={
            "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_3127.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_9576.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383242_1721.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383219_5806.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg",
        "http://img.my.csdn.net/uploads/201407/26/1406383165_7197.jpg","",""
        };
    private DisplayImageOptions options;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.q)//正在加载时展示什么图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//加载出错了出现什么图片
                .cacheInMemory(true) //缓存到内存
                .cacheOnDisk(true)   //是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        GridView gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(new MyAdapter());
        }
class MyAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return images.length;
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
            convertView = View.inflate(MainActivity.this, R.layout.item, null);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        ImageLoader.getInstance().displayImage(images[position],iv,options);

        return convertView;
    }
}
}
