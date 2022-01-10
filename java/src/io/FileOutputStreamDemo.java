package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream是字节输出流，FileOutputStream是它的子类，本类用于示范FileOutputStream的常规用法
 * Created by Mo on 2017/5/7.
 */
public class FileOutputStreamDemo {

    //FileOutputStream基础用法--向文件写入信息
    public static void addContentToFile() throws IOException {
        File file = new File("D://FolderA//test.txt");//首先尝试获取文件对象
        if (!file.exists()) file.createNewFile();//若文件不存在则创建文件
        OutputStream outputStream = new FileOutputStream(file, true);//获取文件的输出流
        byte[] data = {'a', 's', 'd'};//准备即将写入的信息
        outputStream.write(data);//向文件中添加信息
        outputStream.close();//使用完毕后关闭
    }


    public static void main(String[] args) throws IOException {
        addContentToFile();
    }
}
