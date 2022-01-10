package socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientDemo {
    private static int MAXTRIES = 10;//最大尝试次数

    public static void main(String[] args) throws Exception {
        InetAddress aInetAddress = InetAddress.getByName("www.baidu.com");

        // 1. 构造UDP DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();

        // 2。指定timeout时间，防止进入无限等待状态
        socket.setSoTimeout(600);

        // 3. 构造收发的报文对象
        byte[] buf = new byte[1024];
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, aInetAddress, 8080);
        DatagramPacket receivePacket = new DatagramPacket(new byte[buf.length], buf.length);

        // 4.指定尝试的次数
        int tries = 0;
        boolean receivedResponse = false;
        do {
            try {
                socket.send(sendPacket);
                socket.receive(receivePacket);

                if (!receivePacket.getAddress().equals(aInetAddress)) {
                    throw new Exception("Received packet from an unknown source");
                }
                receivedResponse = true;
            } catch (Exception e) {
                tries += 1;
                System.out.println("Timed out, " + (MAXTRIES - tries) + "");
            }
        } while ((!receivedResponse) && (tries < MAXTRIES));

        // 根据是否接收到报文进行反馈
        if (receivedResponse) {
            System.out.println("Received: " + new String(receivePacket.getData()));
        } else {
            System.out.println("No response -- giving up.");
        }

        // 5. 关闭socket
        socket.close();

    }
    /**
     * client端
     *
     * UDP客户端的步骤也比较简单，主要包括下面3步：
     *
     * 1. 构造DatagramSocket实例。
     *
     * 2.通过DatagramSocket实例的send和receive方法发送DatagramPacket报文。
     *
     * 3.结束后，调用DatagramSocket的close方法关闭。
     *
     * 因为和TCP不同，UDP发送报文的时候可以在同一个本地端口随意发送给不同的服务器，
     * 一般不需要在UDP的DatagramSocket的构造函数中指定目的服务器的地址。
     *
     * 另外，UDP客户端还有一个重要的不同就是，TCP客户端发送echo连接消息之后会在调用read方法的时候进入阻塞状态，而UDP这样却不行。
     * 因为UDP中间是可以允许报文丢失的。如果报文丢失了，进程一直在阻塞或者挂起的状态，则进程会永远没法往下走了。
     * 所以会一般设置一个setSoTimeout方法，指定在多久的时间内没有收到报文就放弃。
     * 也可以通过指定一个数字，循环指定的次数来读取报文，读到就返回，否则就放弃
     */
}
