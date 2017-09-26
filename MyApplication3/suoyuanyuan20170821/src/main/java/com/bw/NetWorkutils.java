package com.bw;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 索园 on 2017/8/21.
 */
public class NetWorkutils {

    static String path="http://v3.wufazhuce.com:8000/api/reading/index/?version=3.5.0&platform=android";
    private static ByteArrayOutputStream baos;

    public static String getjson(){
        try {
            URL url=new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            int responseCode = connection.getResponseCode();
            baos = new ByteArrayOutputStream();
            if(responseCode==200){
                InputStream is = connection.getInputStream();
                int len;
                byte[] buffer=new byte[1024];
                while ((len=is.read(buffer))!=-1){
                    baos.write(buffer,0,len);

                }
            }
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }
}
