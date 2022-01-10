package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * ObjectInputStream : 对象输入流
 * Created by Mo on 2017/5/10.
 */
public class ObjectInputStreamDemo {

    //展示ObjectInputStream的基本使用
    public static void readHeroType() throws IOException, ClassNotFoundException {
        File file = new File("D://FolderA//objectOutputStream.ini");//获取文件对象
        if (!file.exists()) return;//若文件对象不存在则直接返回

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));//建立流
        while (true) {//循环读取数据
            Object heroType = objectInputStream.readObject();
            if (null == heroType) break;
            System.out.println("读取到数据:" + heroType);
        }
        objectInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        readHeroType();
    }

}
