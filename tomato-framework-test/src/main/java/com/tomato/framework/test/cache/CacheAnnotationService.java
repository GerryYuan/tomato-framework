package com.tomato.framework.test.cache;

import com.tomato.framework.plugin.cache.annotation.RemoteCache;
import com.tomato.framework.test.model.Demo;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheAnnotationService {

    @RemoteCache(key = "#aaa+':cache'")
    public Demo testRemoteCache(String aaa, Boolean bbb) {
        log.info("测试远程缓存 注解!");
        Demo demo = new Demo();
        demo.setId(1);
        demo.setIsOK(bbb);
        demo.setName(aaa);
        demo.setTime(new Date());
        return demo;
    }

}
