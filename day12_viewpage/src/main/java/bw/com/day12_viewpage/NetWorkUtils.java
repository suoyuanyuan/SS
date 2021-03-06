package bw.com.day12_viewpage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 索园 on 2017/8/14.
 */
public class NetWorkUtils {

    static String path="http://www.meirixue.com/api.php?c=index&a=index";
    private static ByteArrayOutputStream baos;
    public static  String getjson() {

        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = connection.getInputStream();
                baos = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            }
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }
}
