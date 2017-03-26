package com.tomato.framework.plugin.cache.ops;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by gerry
 */
public interface RemoteCacheValueOps<V> {


    /**
     * Set {@code value} for {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param value
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    void vset(String key, V value);

    /**
     * Set the {@code value} and expiration {@code timeout} for {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param value
     * @param timeout
     * @param unit must not be {@literal null}.
     * @see <a href="http://redis.io/commands/setex">Redis Documentation: SETEX</a>
     */
    void vset(String key, V value, long timeout, TimeUnit unit);

    /**
     * Set {@code key} to hold the string {@code value} if {@code key} is absent.
     *
     * @param key must not be {@literal null}.
     * @param value
     * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    Boolean vsetIfAbsent(String key, V value);

    /**
     * Set multiple keys to multiple values using key-value pairs provided in {@code tuple}.
     *
     * @param map must not be {@literal null}.
     * @see <a href="http://redis.io/commands/mset">Redis Documentation: MSET</a>
     */
    void vmultiSet(Map<? extends String, ? extends V> map);

    /**
     * Set multiple keys to multiple values using key-value pairs provided in {@code tuple} only if the provided key does
     * not exist.
     *
     * @param map must not be {@literal null}.
     * @see <a href="http://redis.io/commands/mset">Redis Documentation: MSET</a>
     */
    Boolean vmultiSetIfAbsent(Map<? extends String, ? extends V> map);

    /**
     * Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/get">Redis Documentation: GET</a>
     */
    V vget(String key);

    /**
     * Set {@code value} of {@code key} and return its old value.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/getset">Redis Documentation: GETSET</a>
     */
    V vgetAndSet(String key, V value);

    /**
     * Get multiple {@code keys}. Values are returned in the order of the requested keys.
     *
     * @param keys must not be {@literal null}.
     * @see <a href="http://redis.io/commands/mget">Redis Documentation: MGET</a>
     */
    List<V> vmultiGet(Collection<String> keys);

    /**
     * Increment an integer value stored as string value under {@code key} by {@code delta}.
     *
     * @param key must not be {@literal null}.
     * @param delta
     * @see <a href="http://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    Long vincrement(String key, long delta);

    /**
     * Increment a floating point number value stored as string value under {@code key} by {@code delta}.
     *
     * @param key must not be {@literal null}.
     * @param delta
     * @see <a href="http://redis.io/commands/incrbyfloar">Redis Documentation: INCRBYFLOAT</a>
     */
    Double vincrement(String key, double delta);

    /**
     * Append a {@code value} to {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param value
     * @see <a href="http://redis.io/commands/append">Redis Documentation: APPEND</a>
     */
    Integer vappend(String key, String value);

    /**
     * Get a substring of value of {@code key} between {@code begin} and {@code end}.
     *
     * @param key must not be {@literal null}.
     * @param start
     * @param end
     * @see <a href="http://redis.io/commands/getrange">Redis Documentation: GETRANGE</a>
     */
    String vget(String key, long start, long end);

    /**
     * Overwrite parts of {@code key} starting at the specified {@code offset} with given {@code value}.
     *
     * @param key must not be {@literal null}.
     * @param value
     * @param offset
     * @see <a href="http://redis.io/commands/setrange">Redis Documentation: SETRANGE</a>
     */
    void vset(String key, V value, long offset);

    /**
     * Get the length of the value stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/strlen">Redis Documentation: STRLEN</a>
     */
    Long vsize(String key);

    /**
     * Sets the bit at {@code offset} in value stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param offset
     * @param value
     * @since 1.5
     * @see <a href="http://redis.io/commands/setbit">Redis Documentation: SETBIT</a>
     */
    Boolean vsetBit(String key, long offset, boolean value);

    /**
     * Get the bit value at {@code offset} of value at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param offset
     * @since 1.5
     * @see <a href="http://redis.io/commands/setbit">Redis Documentation: GETBIT</a>
     */
    Boolean vgetBit(String key, long offset);

}
