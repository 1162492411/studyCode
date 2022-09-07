package com.study.mapstruct.sample.methodReturnToField;

public class BUtil {
    public static String extractFromNo(Long input){
        String inputString = input.toString();
        return inputString.length() >= 8 ? inputString.substring(0,8) : "20220101";
    }
}
