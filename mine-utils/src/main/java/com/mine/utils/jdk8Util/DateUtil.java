package com.mine.utils.jdk8Util;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Created by jiangqd on 2019/3/22.
 */
public class DateUtil {

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
    }

}
