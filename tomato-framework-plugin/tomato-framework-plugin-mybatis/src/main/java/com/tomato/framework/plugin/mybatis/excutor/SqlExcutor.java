package com.tomato.framework.plugin.mybatis.excutor;

import java.util.List;

public interface SqlExcutor {
    
    <T> List<T> selectList(Object param);
    
}
