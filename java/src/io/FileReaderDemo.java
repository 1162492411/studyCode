package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reader是字符流，FileReader是Reader的子类，本类用于示范FileReader的常规用法
 * Created by Mo on 2017/5/7.
 */
public class FileReaderDemo {

    public static void readFile() throws IOException {
        //获取文件对象
        File file = new File("D://FolderA//test.txt");
        //若文件不存在，提示并直接返回
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        //获取FileReader流
        FileReader fileReader = new FileReader(file);
        //建立char[]数组来保存读取的信息
        char[] temp = new char[2];
        //循环读取文件的信息
        while (-1 != fileReader.read(temp)) {
            System.out.println(String.valueOf(temp));
        }
        //读取完毕后关闭流
        fileReader.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
    }

}
