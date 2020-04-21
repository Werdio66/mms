package com.lx.mms.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lx.mms.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 *  将指定的类转换为 json 字符串
 *  将字符串对应的数据封装到对象中
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 排除为空的字段
    static {

        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
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
            log.error("将对象转换为字符串错误，", e);
            return null;
        }
    }

    /**
     *  将字符串转换为对象
     * @param <T>                   传入对象的类型
     * @param json                  json 字符串
     * @param typeReference         类型引用
     * @return                      对象
     */
    public static <T> T string2Obj(String json, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(json)){
            throw new ParamException("将字符串转换为对象错误 : json 数据为空");
        }

        try {
            return typeReference.getType().equals(String.class) ? (T) json : objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.info("将字符串：{} 转换为对象", json);
            log.error("将字符串转换为对象错误，", e);
            throw new ParamException("将字符串转换为对象错误，" + e.getMessage());
        }
    }
}
