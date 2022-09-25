package com.study.jackson.sample.resolveByOther;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoParam extends InfoReqBaseParam {

    private String name;

    private Integer classNumber;

}