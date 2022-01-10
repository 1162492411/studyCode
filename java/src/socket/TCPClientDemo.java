package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class TCPClientDemo {

    public static void main(String[] args) throws Exception {
        //指定ip和端口，建立一个socket对象
        Socket aSocket = new Socket("127.0.0.1", 9000);

        // 获取socket的输入输出流，进行数据的读写
        BufferedReader br = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(aSocket.getOutputStream()));

        // 将收到的信息读取
        while (br.readLine() != null)
            bw.write(br.readLine());

        //使用close关闭
        aSocket.close();
    }
    /**
     * 客户端的请求过程稍微有点不一样：
     *
     * 1.构建Socket实例，通过指定的远程服务器地址和端口来建立连接。
     *
     * 2.通过Socket实例包含的InputStream和OutputStream来进行数据的读写。
     *
     * 3.操作结束后调用socket实例的close方法，关闭。
     */
}
