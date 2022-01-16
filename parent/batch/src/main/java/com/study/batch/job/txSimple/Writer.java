package com.study.batch.job.txSimple;

import com.study.batch.entity.Teacher;
import com.study.batch.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class Writer implements ItemWriter<Teacher> {
    @Autowired
    private TeacherMapper teacherMapper;

    //目前这种方式会报错，Cannot change the ExecutorType when there is an existing transaction
    @Override
    public void write(List<? extends Teacher> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        for (Teacher teacher : list) {
            teacherMapper.insert(teacher);
        }
//        list.stream().filter(Objects::nonNull).forEach(e -> teacherMapper.insert(e));
    }
}
