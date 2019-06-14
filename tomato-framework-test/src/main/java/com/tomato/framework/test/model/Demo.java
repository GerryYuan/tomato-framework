package com.tomato.framework.test.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author gerry
 * @version 1.0, 2017-12-14 13:53
 * @since com.hujiang 1.0.0
 */
@Data
public class Demo implements Serializable{

    private Integer id;

    private String name;

    private Date time;

    private Boolean isOK;

}
