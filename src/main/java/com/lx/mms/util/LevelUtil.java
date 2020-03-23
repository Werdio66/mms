package com.lx.mms.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    // 部门之间的分隔符
    public static String separator = ".";
    // 部门等级
    public static String root = "0";

    public static String caculatateLevel(String parentLevel, Long parentId){
        if (StringUtils.isBlank(parentLevel)){

            return root;
        }
        // parentLevel.parentId
        return StringUtils.join(parentLevel, separator, parentId);
    }

}
