package bw.com.day09_dome;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 索园 on 2017/8/11.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)
                .memoryCacheSize(1024)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
