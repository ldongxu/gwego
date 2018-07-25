package com.gwego.util;


import com.gwego.exception.ParamException;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author liudongxu06
 * @date 2017/9/29
 */
public class ValidationUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            StringBuilder sb = new StringBuilder(obj.getClass().getName() + "参数验证错误\n");
            for (ConstraintViolation<T> c : set) {
                sb.append(c.getMessage());
                sb.append("\n");
            }
            throw new RuntimeException(sb.toString());
        }
    }
    
    /**
     * @author panzhigao-wb
     * @param obj
     * @param groups
     */
    public static <T> void validateParam(T obj,Class<?>... groups){
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            StringBuilder sb = new StringBuilder("参数验证错误\n");
            for (ConstraintViolation<T> c : set) {
                sb.append(c.getMessage());
                sb.append("\n");
            }
            throw new ParamException(sb.toString());
        }
        System.out.println("校验成功");
    }
}