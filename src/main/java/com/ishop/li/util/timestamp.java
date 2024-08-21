package com.ishop.li.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class timestamp {
    public static String timestamp = LocalDateTime.now(ZoneId.of("Asia/Vientiane"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
