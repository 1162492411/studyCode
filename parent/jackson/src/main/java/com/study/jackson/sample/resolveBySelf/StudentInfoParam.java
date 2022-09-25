package com.study.jackson.sample.resolveBySelf;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoParam extends InfoReqBaseParam{

    private String name;

    private String no;

}
