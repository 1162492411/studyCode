
# File

在Java中，文件和文件夹均用File表示

# Stream

在Java中，当不同介质之间进行数据交互时用流来实现。站在程序的角度，从文件向程序中输入信息(即读取文件中的数据)就是**输入流**,从程序向文件输出信息(即将程序中的数据写入到文件中)叫做**输出流**

流的分类
* 字节流
    * 该流以**字节形式**来读入读出数据
    * InputStream--字节输入流--常用它的子类FileInputStream
    * OutputStream--字节输出流--常用它的子类FileOutputStream
* 字符流
    * 该流以**字符形式** 来读入读出数据
    * Reader--字符输入流--常用它的子类FileReader
    * Writer--字符输出流--常用它的子类FileWriter
* 缓存流
    * 缓存流的目的是提高读写效率，每次读取数据都访问缓存，只有缓存中的数据读取完毕才访问硬盘读取数据
    * BufferedInputStream--缓存字节输入流
    * BufferedOutputStream--缓存字节输出流
    * BufferedReader--缓存字符输入流
    * BufferedWriter--缓存字符输出流
* 数据流
    * 该流用于读写**Java数据类型的数据**,如int、boolean等
    * DataInputStream--数据输入流
    * DataOutputStream--数据输出流
* 对象流
    * 该流把对象以**流**的形式在不同介质之间交互
    * 对象以流的形式进行传输称为`序列化`，将流转化为对象称为`反序列化`
    * ObjectInputStream--对象输入流
    * ObjectOutputStream--对象输出流

