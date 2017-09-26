package com.example.gkjexam;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 郭康杰 on 2017/8/18.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)   //开启三个线程
                .memoryCacheSize(1024*2)  //设置 内存缓存的大小
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
