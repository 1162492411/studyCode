package io;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataOutputStream : 数据输入流
 * Created by Mo on 2017/5/10.
 */
public class DataOutputStreamDemo {

    //展示数据输入流的基本用法
    public static void write() throws IOException {
        File file = new File("D://FolderA//dataOutputStreamDemo.txt");//获取文件
        if (!file.exists()) file.createNewFile();//若文件不存在则创建文件

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file, true));//创建流，以追加内容方式
        dataOutputStream.writeInt(56);//写入数据
        dataOutputStream.writeUTF("String--57");
        dataOutputStream.writeBoolean(Boolean.TRUE);
        dataOutputStream.close();//使用完毕后关闭流
    }


    public static void main(String[] args) throws IOException {
        write();
    }
}
