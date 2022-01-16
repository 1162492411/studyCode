package com.study.batch.job.simple;

import com.study.batch.entity.User;
import org.springframework.batch.item.support.ListItemReader;

import java.util.ArrayList;
import java.util.List;

public class SimpleReader<T> extends ListItemReader<User> {

    private static List<User> userList = new ArrayList<>();


    static {
        userList.add(com.study.batch.entity.User.builder().id(1).build());
        userList.add(com.study.batch.entity.User.builder().id(2).build());
        userList.add(com.study.batch.entity.User.builder().id(3).build());
        userList.add(com.study.batch.entity.User.builder().id(4).build());
        userList.add(com.study.batch.entity.User.builder().id(5).build());
    }

    public SimpleReader() {
        super(userList);
    }

}
