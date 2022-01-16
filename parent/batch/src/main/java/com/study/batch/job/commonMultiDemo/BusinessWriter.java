package com.study.batch.job.commonMultiDemo;

import com.study.batch.entity.Teacher;
import com.study.batch.job.commonSupport.AbstractListItemWriter;
import com.study.batch.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @author zyg
 */
public class BusinessWriter<K> extends AbstractListItemWriter<Teacher> {
    @Autowired
    private ITeacherService teacherService;

    @Override
    protected void doWrite(List<? extends Teacher> item) {
        teacherService.saveBatch((Collection<Teacher>) item);
    }
}
