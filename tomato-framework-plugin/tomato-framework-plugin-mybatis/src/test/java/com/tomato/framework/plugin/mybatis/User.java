package com.tomato.framework.plugin.mybatis;

import java.util.Date;
import lombok.Data;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:25
 */
@Data
public class User {
    
    private Integer id;
    
    private String user_name;
    
    private Integer age;
    
    private Date create_time;
    
    private Date update_time;
    
}
