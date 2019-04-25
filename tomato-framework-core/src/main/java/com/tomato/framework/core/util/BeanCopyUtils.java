package com.tomato.framework.core.util;

import java.util.Objects;
import org.springframework.cglib.beans.BeanCopier;

public class BeanCopyUtils {
    
    public static void copyProperties(Object source, Object target) {
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(target, "target must not be null");
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopier.copy(source, target, null);
    }
    
    
}
