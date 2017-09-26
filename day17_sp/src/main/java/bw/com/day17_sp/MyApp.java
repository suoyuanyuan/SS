package bw.com.day17_sp;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 索园 on 2017/8/22.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)
                .memoryCacheSize(1024*2)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
