package com.study.batch.utils;

import cn.hutool.core.io.file.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: SpringBeanUtil
 * @author: Wzq
 * @create: 2019-10-08 16:24
 */
@Slf4j
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
    }
    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static void main(String[] args) {
//        String prefixSql = "goods_code IN ";
//
//        FileReader fileReader = new FileReader("/Users/zyg/Downloads/a.csv");
//        List<String> readLines = fileReader.readLines();
//        readLines.remove(0);
//        List<String> finalSqlList = new ArrayList<>(3);
//        List<String> unionSqlList = new ArrayList<>(20);
//        Function<List<String>,Integer> function = subList ->{
//            StringBuilder stringBuilder = new StringBuilder(prefixSql);
//            StringBuilder stringBuilder1 = new StringBuilder();
//            stringBuilder.append("(");
//            for (String item : subList) {
//                String[] strings = item.split(",");
//                stringBuilder.append("'").append(strings[strings.length - 1]).append("',");
//                stringBuilder1.append("select ").append(strings[strings.length - 1]).append(" as g union ");
//            }
//            stringBuilder.append("'1'");
//            stringBuilder.append(")");
//            //拼接
////            stringBuilder.append(")t1");
//            stringBuilder1.append("select 1");
//
//            finalSqlList.add(stringBuilder.toString());
//            unionSqlList.add(stringBuilder1.toString());
//            return 1;
//        };
//        ListPagingUtil.listPolling(readLines,199,function);
//
//        for (int i = 0; i < finalSqlList.size(); i++) {
//            String paramSql = finalSqlList.get(i);
//            String unionSql = unionSqlList.get(i);
//            String contactSql = "select group_concat(g) from (select t1.g from (" + unionSql +
//                ") t1 where t1.g not in (select goods_code from tb_reference_price  where " + paramSql + ")) t2";
//            String countSql = "select count(g) from (select t1.g from (" + unionSql +
//                ") t1 where t1.g not in (select goods_code from tb_reference_price  where " + paramSql + ")) t2";
//            log.info((i+1) + "次的总数的sql是\n" + countSql + "\n");
//            log.info("拼接的sql是\n" + contactSql + "\n");
//            log.info("===============\n\n");
//        }



        FileReader fileReader = new FileReader("/Users/zyg/Downloads/finql.txt");
        List<String> readLines = fileReader.readLines();
        String[] strings = readLines.get(0).split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string + ",\n");
        }
        log.info("最终生产:\n " +stringBuilder.toString());
    }

}
