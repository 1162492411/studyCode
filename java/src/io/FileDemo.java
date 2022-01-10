package io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File类的常见用法
 * Created by Mo on 2017/5/6.
 */
public class FileDemo {

    //创建文件夹/文件对象
    public static void createFile() throws IOException {
        //使用绝对路径创建文件夹/文件对象
        File fa = new File("D://FolderA");
        fa.mkdir();//创建文件夹，但是如果父文件夹不存在，并不会创建该子文件夹
        System.out.println("fa的绝对路径是:" + fa.getAbsolutePath());

        //使用相对路径创建文件夹/文件对象
        File fb = new File("FolderB");
        fb.mkdirs();//创建文件夹，无论父文件夹是否存在一定创建
        System.out.println("fb的绝对路径是:" + fb.getAbsolutePath());

        //在父文件下创建文件夹/文件对象(父文件以File形式传入)
        File fc = new File(fb, "FolderC");
        fc.createNewFile();//创建文件，但是如果父文件夹不存在，并不会创建该文件
        System.out.println("fc的绝对路径是:" + fc.getAbsolutePath());

        //在父文件下创建文件夹/文件对象(父文件以String形式传入)
        File fd = new File(fa.getAbsolutePath(), "FolderD");
        System.out.println("fd的绝对路径是:" + fd.getAbsolutePath());

    }

    //查看文件属性
    public static void fileInfo() {
        File file = new File("H://demo2.txt");//创建文件对象
        if (!file.exists()) try {//若文件不存在则创建
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("该文件的名字是:" + file.getName());
        System.out.println("该文件是否存在:" + file.exists());
        System.out.println("该文件是否是文件夹:" + file.isDirectory());
        System.out.println("该文件的绝对路径是:" + file.getAbsolutePath());
        System.out.println("该文件的大小是:" + file.length());
        System.out.println("该文件上次修改时间是:" + new Date(file.lastModified()));
    }

    //操作文件
    public void fileAction() throws IOException {
        File file = new File("G://temp/static");

        String[] list = file.list();//以字符串数组形式返回指定文件夹下的所有文件及文件夹(不包含子文件及子文件夹)
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }

        File[] files = file.listFiles();//以文件数组形式返回指定文件夹下的所有文件及文件夹(不包含子文件及子文件夹)
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }

        System.out.println("该文件的父路径是:" + file.getParent());//返回字符串形式的父类文件夹路径

        System.out.println("该文件的父路径文件是:" + file.getParentFile());//返回文件形式的父类文件夹对象

        File[] roots = file.listRoots();//获取设备的所有盘符
        for (int i = 0; i < roots.length; i++) {
            System.out.printf(roots[i] + "\t");
        }

        for (int i = 0; i < 5; i++) {
            File tempFile = file.createTempFile("static-", ".txt");//创建指定前缀和后缀的临时文件
            System.out.println("本次创建了临时文件:" + tempFile.getAbsolutePath());
        }

        file.deleteOnExit();//JVM结束时删除文件，常用于创建的临时文件的删除

    }


    public static void main(String[] args) throws IOException {
        FileDemo fileDemo = new FileDemo();
        FileDemo.createFile();
        FileDemo.fileInfo();
        fileDemo.fileAction();

    }

}
