package com.asyahir.statement_query_service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class RowHashGeneratorUtil {

    public static String generateRowHash(String... items) {
        String joined = String.join("|", items);
        log.info("string to hashing: {}", joined);
        return DigestUtils.md2Hex(joined.toString());
    }
}
