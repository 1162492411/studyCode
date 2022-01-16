package com.study.batch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.batch.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {


    List<User> selectByIdRange(Integer idLeft, Integer idRight, Integer skipRows, Integer pageSize);

    Integer selectMinId();

    Integer selectMaxId();


}
