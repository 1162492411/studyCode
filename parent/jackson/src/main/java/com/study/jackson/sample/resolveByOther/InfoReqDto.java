package com.study.jackson.sample.resolveByOther;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoReqDto<T extends InfoReqBaseParam> {

    private String reqType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            property = "reqType",
            visible = true,
            defaultImpl = com.study.jackson.sample.resolveBySelf.InfoReqBaseParam.class,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY
    )
    private T reqParam;

}
