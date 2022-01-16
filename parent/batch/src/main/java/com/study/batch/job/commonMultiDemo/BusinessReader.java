package com.study.batch.job.commonMultiDemo;

import com.study.batch.entity.User;
import com.study.batch.job.commonSupport.AbstractPeekPagingListReader;
import com.study.batch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BusinessReader<K> extends AbstractPeekPagingListReader<User> {

    @Autowired
    private IUserService userService;

    @Override
    protected List<User> doReadPage() {
        int idLeft = (int) getParameterValues().get("leftId");
        int idRight = (int) getParameterValues().get("rightId");
        return userService.selectByIdRange(idLeft,idRight,getStartNumber(),getPageSize());
    }

}
