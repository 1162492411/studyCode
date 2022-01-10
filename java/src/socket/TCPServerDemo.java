package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDemo {

    public static void main(String[] args) throws Exception {
        // 建立一个端口为8888的serverSocket对象
        ServerSocket aServerSocket = new ServerSocket(8888);

        while (true) {
            // 使用accept方法返回Socket对象,建立服务器端和客户端的连接
            Socket aSocket = aServerSocket.accept();

            System.out.println("客户端地址为：" + aSocket.getRemoteSocketAddress());
            // 获取socket的输入输出流，进行数据的读写
            BufferedReader br = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(aSocket.getOutputStream()));

            // 将收到的信息读取
            while (br.readLine() != null) {
                bw.write(br.readLine());
            }

            // 使用close关闭
            aSocket.close();
            aServerSocket.close();
        }

    }
    /**
     * Server端所要做的事情主要是建立一个通信的端点，然后等待客户端发送的请求。典型的处理步骤如下：
     *
     * 1. 构建一个ServerSocket实例，指定本地的端口。这个socket就是用来监听指定端口的连接请求的。
     *
     * 2.重复如下几个步骤：
     *
     * a.
     * 调用socket的accept()方法来获得下面客户端的连接请求。通过accept()方法返回的socket实例，建立了一个和客户端的新连接。
     *
     * b.通过这个返回的socket实例获取InputStream和OutputStream,可以通过这两个stream来分别读和写数据。
     *
     * c.结束的时候调用socket实例的close()方法关闭socket连接。
     */
}
