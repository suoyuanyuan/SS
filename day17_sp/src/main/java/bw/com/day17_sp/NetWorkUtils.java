package bw.com.day17_sp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 索园 on 2017/8/22.
 */
public class NetWorkUtils {
    public static String getJson(){
        String path="http://www.meirixue.com/api.php?c=index&a=index";
        try {
            URL  url=new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            byte[] buffer=new byte[1024];
            int len;
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            if(responseCode==200){
                InputStream is = connection.getInputStream();
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
}
