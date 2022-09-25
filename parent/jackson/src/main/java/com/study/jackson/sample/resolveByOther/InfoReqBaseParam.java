package com.study.jackson.sample.resolveByOther;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentInfoParam.class, name = "code_student"),
        @JsonSubTypes.Type(value = TeacherInfoParam.class, name = "code_teacher")
})
public class InfoReqBaseParam {

}
