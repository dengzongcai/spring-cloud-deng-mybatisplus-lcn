package com.deng.currency.utils;

import com.deng.commons.entitys.SysProperty;
import com.deng.currency.biz.SysPropertyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import java.util.*;

/**
 * @Auther ZongCai
 * @Date 2021/8/22
 */
public class PropertyDbUtil {
    @Autowired
    private static SysPropertyBiz syspropertybiz;

    /**
     * 模拟从db中获取邮件配置信息
     *
     * @return
     */
    public static Map<String, Object> getMailInfoFromDb() {
        List<SysProperty> sysProperties = syspropertybiz.queryListSysProperty();
        Map<String, Object> result = new HashMap<>();
        if (CollectionUtils.isEmpty(sysProperties)){
            return result;
        }
        Iterator<SysProperty> iterator = sysProperties.iterator();
        while (iterator.hasNext()) {
            SysProperty property = iterator.next();
            if (Objects.nonNull(property)){
                result.put(property.getKey(), property.getValues());
            }
        }
        return result;
    }
}
