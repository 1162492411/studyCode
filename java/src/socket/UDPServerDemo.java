package socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerDemo {

    public static void main(String[] args) throws Exception {
        //建立一个指定端口的DatagramSocket对象
        DatagramSocket aDatagramSocket = new DatagramSocket(8889);

        byte[] buf = new byte[1024];
        //建立一个DatagramPacket对象，收发报文
        DatagramPacket aDatagramPacket = new DatagramPacket(buf, 1024);

        while (true) {
            // 收报文  
            aDatagramSocket.receive(aDatagramPacket);
            System.out.println("Handling client at " + aDatagramPacket.getAddress().getHostAddress()
                    + " on port " + aDatagramPacket.getPort());

            // 4. 发报文  
            aDatagramSocket.send(aDatagramPacket);
            aDatagramPacket.setLength(1024);
        }
    }
    /**
     * UDP
     *
     * UDP和TCP有两个典型的区别，一个就是它不需要建立连接，另外就是它在每次收发的报文都保留了消息的边界。
     *
     * server端:
     *
     * 因为UDP协议不需要建立连接，它的过程如下：
     *
     * 1. 构造DatagramSocket实例，指定本地端口。
     *
     * 2.通过DatagramSocket实例的receive方法接收DatagramPacket,DatagramPacket中间就包含了通信的内容。
     *
     * 3. 通过DatagramSocket的send和receive方法来收和发DatagramPacket.
     */
}
