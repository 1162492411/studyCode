package com.study.jackson.sample.resolveByOther;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("")
public class IOtherController implements IOtherApi {

    @Override
    public void receiveDto(@RequestBody InfoReqDto req) throws JsonProcessingException {
        log.info("接收到入参:{}",new ObjectMapper().writeValueAsString(req));
        if(Objects.isNull(req) || StringUtils.isEmpty(req.getReqType())){
            return;
        }
        switch (req.getReqType()){
            case "code_student" :
                log.info("mockStudentMethod,object type:{}", req.getReqParam().getClass());
                break;
            case "code_teacher" :
                log.info("mockTeacherMethod,object type:{}", req.getReqParam().getClass());
                break;
            default: log.info("no method match"); break;
        }
    }
}
