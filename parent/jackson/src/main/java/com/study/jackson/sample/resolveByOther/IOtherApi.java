package com.study.jackson.sample.resolveByOther;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public interface IOtherApi {

    @RequestMapping("receiveDto")
    void receiveDto(@RequestBody InfoReqDto req) throws JsonProcessingException;

}
