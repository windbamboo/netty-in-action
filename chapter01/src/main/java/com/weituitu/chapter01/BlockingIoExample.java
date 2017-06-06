package com.weituitu.chapter01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liuguozhu on 2017/5/24.
 */
public class BlockingIoExample {

    public void serve(int portNumber) throws IOException {
        //创建一个新的 ServerSocket，用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        //对accept()方法的调用将被阻塞，直到一个连接建立
        Socket clientSocket = serverSocket.accept();
        //这些流对象都派生于该套接字的流对象
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        //处理循环开始
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            //请求被传递给服务器的处理方法
            response = processRequest(request);
            //服务器的响应被发送给了客户端
            out.println(response);
            //继续执行处理循环
        }
    }

    private String processRequest(String request) {
        return "Processed";
    }
}
