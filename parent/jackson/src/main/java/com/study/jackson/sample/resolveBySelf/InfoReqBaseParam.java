package com.study.jackson.sample.resolveBySelf;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true,
        defaultImpl = InfoReqBaseParam.class, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentInfoParam.class, name = "code_student"),
        @JsonSubTypes.Type(value = TeacherInfoParam.class, name = "code_teacher")
})
public class InfoReqBaseParam {

    private String type;

}
