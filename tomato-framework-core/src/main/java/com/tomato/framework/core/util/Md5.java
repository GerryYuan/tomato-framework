package com.tomato.framework.core.util;

import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Md5 {
    
    public static String md5(String msg) {
        Assert.notNull(msg, "The 'msg' must not be null!");
        return Hashing.md5().newHasher().putString(msg, Charset.defaultCharset()).hash().toString();
    }
}
