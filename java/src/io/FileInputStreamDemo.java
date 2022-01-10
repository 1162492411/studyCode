package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream是字节输入流，FileInputStream是InputStream的子类，本类以FileInputStream为例示范如何使用输出流
 * Created by Mo on 2017/5/7.
 */
public class FileInputStreamDemo {

    public static void printFileInfo() throws Exception {
        File file = new File("D://FolderA//test.txt");//尝试获取文件对象
        if (!file.exists()) {//文件不存在时给出提示并直接返回
            System.out.println("该文件不存在");
            return;
        }
        InputStream inputStream = new FileInputStream(file);//获取文件的输出流
        byte[] all = new byte[(int) file.length()];//创建数组保存文件中的信息
        inputStream.read(all);//使用输出流读取文件中的信息
        System.out.println("该文件的内容如下:");//输出文件中的信息
        for (byte b : all) {
            System.out.printf(String.valueOf((char) b));
        }
        inputStream.close();//流使用之后进行关闭
    }

    public static void main(String[] args) throws Exception {
        printFileInfo();
    }

}

