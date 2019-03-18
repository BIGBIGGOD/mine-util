package com.mine.test;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/2/21.
 */
@Data
@Slf4j
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private String name;
    private String pwd;

}
