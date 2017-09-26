package bw.com.day4_serversocket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by 索园 on 2017/8/4.
 */
public class ServerSocket {




    public static void main(String[] args){
        try {
            java.net.ServerSocket socket=new java.net.ServerSocket(6666);
            //阻塞的
            Socket s=socket.accept();
            InputStream inputStream = s.getInputStream();
            OutputStream outputStream = s.getOutputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            PrintStream ps=new PrintStream(outputStream);
            //得到客户端发过来的消息
            String readLine = br.readLine();
            //把读出来的字符串进行反转
            String string=new StringBuilder(readLine).reverse().toString();
            //发给客户端
            ps.print(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
