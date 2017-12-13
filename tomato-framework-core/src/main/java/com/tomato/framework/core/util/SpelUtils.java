package com.tomato.framework.core.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpelUtils {

    // 使用SPEL进行key的解析
    private static ExpressionParser expressionParser = new SpelExpressionParser();

    private static LocalVariableTableParameterNameDiscoverer localVariableParameterName = new LocalVariableTableParameterNameDiscoverer();

    /**
     * @param key
     * @param method
     * @param paraValues
     * @return
     */
    public static Object parseKey(String key, Method method, Object[] paraValues) {
        if (EmptyUtils.isEmpty(method) || EmptyUtils.isEmpty(key)) {
            return null;
        }
        if (EmptyUtils.isEmpty(paraValues)) {
            return key;
        }
        // 获取被拦截方法参数名列表(使用Spring支持类库)
        try {
            String[] paraNames = localVariableParameterName.getParameterNames(method);
            if (EmptyUtils.isEmpty(paraNames)) {
                return null;
            }
            // 把方法参数放入SPEL上下文中
            StandardEvaluationContext context = new StandardEvaluationContext();
            AtomicInteger i = new AtomicInteger(0);
            Arrays.stream(paraNames).forEach(paraName -> context.setVariable(paraName, paraValues[i.getAndAdd(1)]));
            return expressionParser.parseExpression(key).getValue(context);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
