package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * BufferedReader : 缓存字符输入流
 * Created by Mo on 2017/5/10.
 */
public class BufferedReaderDemo {

    //展示BufferedReader的基础使用--按行读取
    public void readeByLine() throws IOException {
        File file = new File("D://FolderA//textWrite.txt"); //获取文件
        if (!file.exists()) return;//若文件不存在则退出
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); //若文件存在，创建BufferedReader
        while (true) {//循环 : 当文件中的数据未被读取完时，每次读取一行
            String temp = bufferedReader.readLine();//取出一行数据
            if (null == temp) break;//当文件被读取完毕时退出循环
            System.out.println("读取到数据:" + temp);//否则打印读取到的该行信息
        }
        bufferedReader.close();  //使用完毕后关闭流

    }

    //展示BufferedReader的基础使用--按字符数组读取
    public void readeByChars() throws IOException {
        File file = new File("D://FolderA//textWrite.txt"); //获取文件
        if (!file.exists()) return;//若文件不存在则退出
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); //若文件存在，创建BufferedReader
        char[] chars = new char[512];//建立一个char[]数组存放读取到的数据
        while (-1 != bufferedReader.read(chars)) {//循环 : 当文件中的数据未被读取完时，每次读取一个char[]的内容
            System.out.println("读取到数据:" + String.valueOf(chars));//否则打印读取到的该行信息
        }
        bufferedReader.close();  //使用完毕后关闭流
    }

    public static void main(String[] args) throws IOException {
        BufferedReaderDemo bufferedReaderDemo = new BufferedReaderDemo();
        bufferedReaderDemo.readeByLine();
        bufferedReaderDemo.readeByChars();
    }

}
