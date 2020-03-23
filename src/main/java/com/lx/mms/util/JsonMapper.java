package com.lx.mms.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 *  将指定的类转换为 json 字符串
 *  将字符串对应的数据封装到对象中
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 排除为空的字段
    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     *  将对象转换为 json 字符串
     * @param obj   转换的对象
     * @param <T>   对象类型
     * @return      json 字符串 / null
     */
    public static <T> String obj2String(T obj){
        if (obj == null){
            return null;
        }

        try {
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("将对象转换为字符串出错：{}", e.getMessage());
            return null;
        }
    }

    /**
     *  将字符串转换为对象
     * @param json                  json 字符串
     * @param typeReference         对象的类型
     * @param <T>                   传入对象的类型
     * @return                      对象
     */
    public static <T> T string2Obj(String json, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(json)){
            return null;
        }

        try {
            return typeReference.getType().equals(String.class) ? (T) json : objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.warn("将字符串：{} 转换为对象：{} 错误：{}",json, typeReference.getType(), e.getMessage());
            return null;
        }
    }

}
