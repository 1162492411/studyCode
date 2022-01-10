package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * ObjectOutputStream : 对象输出流
 * Created by Mo on 2017/5/10.
 */
public class ObjectOutputStreamDemo {

    //展示ObjectOutputStream的基本使用--建立一个JSON对象列表配置文件
    public static void writeHeroType() throws IOException {
        File file = new File("D://FolderA//objectOutputStream.ini");//获取文件对象
        if (!file.exists()) file.createNewFile();//若对象不存在则创建文件

        ObjectOutputStream objectOutputSteram = new ObjectOutputStream(new FileOutputStream(file, false));//建立流
        HeroType[] datas = {new HeroType(0, "AD"), new HeroType(1, "AP"), new HeroType(2, "ADC")};
        for (int i = 0; i < datas.length; i++) {
            objectOutputSteram.writeObject(datas[i]);
        }
        objectOutputSteram.close();
    }

    public static void main(String[] args) throws IOException {
        writeHeroType();
    }

}
