package com.study.jackson.sample.resolveBySelf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("demo")
@Slf4j
public class IDemoController implements IDemoApi{

    @Override
    public void receive(@RequestBody InfoReqBaseParam req) throws JsonProcessingException {
        log.info("接收到入参:{}",new ObjectMapper().writeValueAsString(req));
        if(Objects.isNull(req) || StringUtils.isEmpty(req.getType())){
            return;
        }
        switch (req.getType()){
            case "code_student" :
                log.info("mockStudentMethod,object type:{}", req.getClass());
                break;
            case "code_teacher" :
                log.info("mockTeacherMethod,object type:{}", req.getClass());
                break;
            default: log.info("no method match"); break;
        }
    }

}
