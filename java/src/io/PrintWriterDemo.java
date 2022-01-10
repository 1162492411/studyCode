package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * PrintWriter : 缓存字符输出流
 * Created by Mo on 2017/5/10.
 */
public class PrintWriterDemo {

    //展示PrintWriter基本使用
    public void write() throws IOException {
        File file = new File("D://FolderA//printWriterDemo.txt");//获取文件
        if (!file.exists()) file.createNewFile();//若文件不存在则新建文件
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));//建立一个流--追加数据方式
//        PrintWriter printWriter = new PrintWriter(file)；//若直接写入模式，可以直接传入File参数，内部包装了缓冲字符输出流及缓冲字节输出流
        printWriter.print("aabbc");//添加数据
        printWriter.append(null);//使用append()时若传入的为null，则添加一个字符串"null"
        printWriter.println("xxyyzz");
        printWriter.close();//使用完毕后关闭流
    }


    //展示PrintWriter基本使用--使用flush强制立刻写入数据
    public void writeByFlush() throws IOException {
        File file = new File("D://FolderA//printWriterDemo.txt");//获取文件
        if (!file.exists()) file.createNewFile();//若文件不存在则新建文件
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));//建立一个流--追加数据方式
        printWriter.print("Flush--aabbc");//添加数据
        printWriter.flush();
        printWriter.append(null);//使用append()时若传入的为null，则添加一个字符串"null"
        printWriter.println("Flush--xxyyzz");
        printWriter.flush();
        printWriter.close();//使用完毕后关闭流
    }


    public static void main(String[] args) throws IOException {
        PrintWriterDemo printWriterDemo = new PrintWriterDemo();
//        printWriterDemo.write();
        printWriterDemo.writeByFlush();
    }

}
