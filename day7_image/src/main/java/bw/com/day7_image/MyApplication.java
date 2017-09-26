package bw.com.day7_image;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
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
