package com.deng.commons.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: CommonHelper
 * @Description: 通用工具类
 * @author ZongCai
 * @date 2021/7/19
 */
public class CommonHelper {

    /**
     *使用";"合并字段串列表
     */
    public static String stringJoin(List<String> list)
    {
        String[] arr = new String[1];
        arr = list.toArray(arr);

       return String.join(";", arr);
    }
    /**
     *使用";"拆分字段串
     */
    public static List<String> stringSplit(String string)
    {
        if (org.springframework.util.StringUtils.isEmpty(string)) {
            return Collections.emptyList();
        }
        return Arrays.asList(string.split(";"));
    }

}
