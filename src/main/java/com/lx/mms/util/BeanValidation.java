package com.lx.mms.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lx.mms.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 *  封装参数校验方法
 */
public class BeanValidation {

    private static ValidatorFactory validatorFactory =  Validation.buildDefaultValidatorFactory();

    /**
     *  校验单个对象中的字段
     * @param t
     * @param groups
     * @param <T>
     * @return
     */
    private static <T> Map<String, String>validate(T t, Class<?> ... groups){
        // 拿到验证器
        Validator validator = validatorFactory.getValidator();
        // 拿到校验后的信息
        Set<ConstraintViolation<T>> validates = validator.validate(t, groups);

        if (validates.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, String> errors = new LinkedHashMap<>();
        // 将校验后的错误信息存入 map
        for (ConstraintViolation<T> validate : validates) {
            // 将字段名validate.getPropertyPath().toString() 和
            // 错误信息 validate.getMessage() 存放到 map 中
            errors.put(validate.getPropertyPath().toString(), validate.getMessage());
        }

        return errors;
    }

    /**
     *  校验多个对象，其实就是将所有的对象存入集合中，挨个去校验
     * @param collection
     * @return
     */
    private static Map<String, String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);

        if (collection.size() == 0){
            return Collections.emptyMap();
        }

        Map<String, String> map = null;

        for (Object obj : collection) {
            map = validate(obj, new Class[0]);
        }

        return map;
    }

    /**
     *  校验对象中的字段
     * @param objects   要校验的对象
     * @return          校验对的错误信息，key ：字段名，value ：错误信息
     */
    public static Map<String, String> validateObj(Object ... objects){
        Preconditions.checkNotNull(objects);

        if (objects.length == 1){
            return validate(objects[0], new Class[0]);
        }

        return validateList(Arrays.asList(objects));
    }

    public static void check(Object ... object){
        Map<String, String> map = validateObj(object);

        if (MapUtils.isNotEmpty(map)){
            throw new ParamException(map.toString());
        }
    }
}
