package com.study.jackson.sample.resolveBySelf;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoReqDto<T extends InfoReqBaseParam> {

    private String reqType;

    private T reqParam;

}
