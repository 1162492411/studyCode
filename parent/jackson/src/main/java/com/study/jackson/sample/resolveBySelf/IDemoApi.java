package com.study.jackson.sample.resolveBySelf;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("demo")
@RestController
public interface IDemoApi {

    @RequestMapping("receive")
    void receive(@RequestBody InfoReqBaseParam req) throws JsonProcessingException;

}
