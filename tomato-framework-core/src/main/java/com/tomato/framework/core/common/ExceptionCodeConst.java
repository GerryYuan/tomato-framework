package com.tomato.framework.core.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by gerry
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionCodeConst {

    public static final int SYS_CODE = 50000;

    public static final int DATASOURCE_CODE = 50001;

    public static final int CACHE_CODE = 50002;
    
    public static final int RMI_CODE = 50003;
}
