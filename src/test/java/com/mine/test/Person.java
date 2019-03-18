package com.mine.test;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/2.
 */
@Data
@Slf4j
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String pwd;
    private String nickName;

}
