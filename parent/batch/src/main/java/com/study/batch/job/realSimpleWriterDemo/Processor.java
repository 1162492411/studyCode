package com.study.batch.job.realSimpleWriterDemo;

import com.study.batch.entity.Teacher;
import com.study.batch.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * @author zyg
 */
public class Processor<T,K> implements ItemProcessor<User, Teacher> {

    @Override
    public Teacher process(User user) throws Exception {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(user,teacher);
        return teacher;
    }
}
