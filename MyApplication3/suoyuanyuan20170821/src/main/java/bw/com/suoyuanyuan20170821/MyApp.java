package bw.com.suoyuanyuan20170821;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by 索园 on 2017/8/21.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String s = Environment.getExternalStorageDirectory() + "aa";
        File file=new File(s);
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).discCache(new UnlimitedDiskCache(file)).build();
        ImageLoader.getInstance().init(configuration);
    }
}
