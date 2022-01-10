package io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * DataInputStream : 对象输出流
 * Created by Mo on 2017/5/10.
 */
public class DataInputStreamDemo {

    //展示DataInputStream的基本使用
    public static void read() throws IOException {
        File file = new File("D://FolderA//dataOutputStreamDemo.txt");//获取文件对象
        if (!file.exists()) return;//若对象不存在则直接返回
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        System.out.println("读取到int:" + dataInputStream.readInt());
        System.out.println("读取到String:" + dataInputStream.readUTF());
        dataInputStream.close();
    }

    //展示DataInputStream读取顺序错误的情况下的结果
    public static void readMistake() throws IOException {
        File file = new File("D://FolderA//dataOutputStreamDemo.txt");//获取文件对象
        if (!file.exists()) return;//若对象不存在则直接返回
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        System.out.println("读取到String:" + dataInputStream.readUTF());
        System.out.println("读取到int:" + dataInputStream.readInt());
        dataInputStream.close();
    }

    public static void main(String[] args) throws IOException {
        read();
        readMistake();
    }

}
