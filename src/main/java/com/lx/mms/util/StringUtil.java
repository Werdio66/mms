package com.lx.mms.util;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<Long> strToLongId(String str){
        if (StringUtils.isEmpty(str)){
            return Lists.newArrayList();
        }
        String[] split = str.split(",");
        List<Long> ids = new ArrayList<>();

        for (String s : split) {
            ids.add(Long.parseLong(s));
        }

        return ids;
    }
}
