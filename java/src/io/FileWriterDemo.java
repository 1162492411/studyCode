package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Writer是输出流，FileWriter是Writer的子类，本类用于演示FileWriter的常规用法
 * Created by Mo on 2017/5/7.
 */
public class FileWriterDemo {

    //向文件写入信息
    public static void writeData() throws IOException {
        //获取文件对象
        File file = new File("D://FolderA//textWrite.txt");
        //如果文件不存在则创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        //建立char[]存储将要写入的信息
        char[] temp;
        //获取文件输出流,以追加文件内容方式
        FileWriter fileWriter = new FileWriter(file, true);
        //循环--随机生成数据写入到文件中
        for (int i = 0; i < 8; i++) {
            temp = getRandomChars(512);
            fileWriter.write(temp);
        }
        //使用完毕关闭流
        fileWriter.close();

    }

    //随机生成指定长度的char[]
    private static char[] getRandomChars(int length) {
        char[] result = new char[length];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) Math.abs(random.nextInt(26) + 97);//随机生成ASCII97-122之间的字符，即所有小写字母
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        writeData();
    }


}
