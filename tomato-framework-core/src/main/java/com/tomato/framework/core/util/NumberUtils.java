package com.tomato.framework.core.util;

/**
 * Number 工具类
 *
 * @author yuanguohua
 */
public abstract class NumberUtils {
    
    /**
     * 在两数范围
     * <pre>（ [number1..number2) | [number1..number2] | (number1..number2) | (number1..number2]）</pre>
     *
     * @param isContainsLeft 是否包含number1
     * @param isContainsRight 是否包含number2
     */
    public abstract boolean rang(final Integer number1, final boolean isContainsLeft, final Integer number2,
        final boolean isContainsRight);
    
    /**
     * 在两数范围
     * <pre>（ [number1..number2) | [number1..number2] | (number1..number2) | (number1..number2]）</pre>
     *
     * @param isContainsLeft 是否包含number1
     * @param isContainsRight 是否包含number2
     */
    public abstract boolean rang(final Long number1, final boolean isContainsLeft, final Long number2,
        final boolean isContainsRight);
    
    /**
     * 在两数范围 （a<x<c）
     *
     */
    public abstract boolean rang(final Integer number1, final Integer number2);
    
    /**
     * 在两数范围 （a<x<c）
     *
     */
    public abstract boolean rang(final Long number1, final Long number2);
    
    /**
     * 两数相等
     * <pre>
     *   NumberUtils.fromNullable(null).equal(null) = true
     *   NumberUtils.fromNullable(null).equal(0)   = true
     *   NumberUtils.fromNullable(0).equal(null)  = true
     *   NumberUtils.fromNullable(0).equal(0)  = true
     *   NumberUtils.fromNullable(1).equal(null)  = false
     *   NumberUtils.fromNullable(null).equal(1)  = false
     *   NumberUtils.fromNullable(1).equal(2)  = false
     * </pre>
     *
     */
    public abstract boolean isEqual(final Integer number);
    
    /**
     * 两数相等
     * <pre>
     *   NumberUtils.fromNullable(null).equal(null) = true
     *   NumberUtils.fromNullable(null).equal(0L)   = true
     *   NumberUtils.fromNullable(0L).equal(null)  = true
     *   NumberUtils.fromNullable(0L).equal(0L)  = true
     *   NumberUtils.fromNullable(1L).equal(null)  = false
     *   NumberUtils.fromNullable(null).equal(1L)  = false
     *   NumberUtils.fromNullable(1L).equal(2L)  = false
     * </pre>
     *
     */
    public abstract boolean isEqual(final Long number);
    
    /**
     * 包含某个数
     *
     */
    public abstract boolean contains(final Integer... numbers);
    
    /**
     * 包含某个数
     *
     */
    public abstract boolean contains(final Long... numbers);
    
    /**
     * 两数比较 小于 <
     * <pre>
     *   NumberUtils.fromNullable(null).less(null) = false
     *   NumberUtils.fromNullable(null).less(0)   = false
     *   NumberUtils.fromNullable(0).less(null)  = false
     *   NumberUtils.fromNullable(0).less(0)  = false
     *   NumberUtils.fromNullable(1).less(null)  = false
     *   NumberUtils.fromNullable(null).less(1)  = true
     *   NumberUtils.fromNullable(1).less(2)  = true
     * </pre>
     *
     */
    public abstract boolean less(final Integer number);
    
    /**
     * 两数比较 小于 <
     * <pre>
     *   NumberUtils.fromNullable(null).less(null) = false
     *   NumberUtils.fromNullable(null).less(0L)   = false
     *   NumberUtils.fromNullable(0L).less(null)  = false
     *   NumberUtils.fromNullable(0L).less(0L)  = false
     *   NumberUtils.fromNullable(1L).less(null)  = false
     *   NumberUtils.fromNullable(null).less(1L)  = true
     *   NumberUtils.fromNullable(1L).less(2L)  = true
     * </pre>
     *
     */
    public abstract boolean less(final Long number);
    
    /**
     * 两数比较 小于等于 <=
     * <pre>
     *   NumberUtils.fromNullable(null).lessOrEqualTo(null) = true
     *   NumberUtils.fromNullable(null).lessOrEqualTo(0)   = true
     *   NumberUtils.fromNullable(0).lessOrEqualTo(null)  = true
     *   NumberUtils.fromNullable(0).lessOrEqualTo(0)  = true
     *   NumberUtils.fromNullable(1).lessOrEqualTo(null)  = false
     *   NumberUtils.fromNullable(null).lessOrEqualTo(1)  = true
     *   NumberUtils.fromNullable(1).lessOrEqualTo(2)  = true
     * </pre>
     *
     */
    public abstract boolean lessOrEqualTo(final Integer number);
    
    /**
     * 两数比较 小于等于 <=
     * <pre>
     *   NumberUtils.fromNullable(null).lessOrEqualTo(null) = true
     *   NumberUtils.fromNullable(null).lessOrEqualTo(0L)   = true
     *   NumberUtils.fromNullable(0L).lessOrEqualTo(null)  = true
     *   NumberUtils.fromNullable(0L).lessOrEqualTo(0L)  = true
     *   NumberUtils.fromNullable(1L).lessOrEqualTo(null)  = false
     *   NumberUtils.fromNullable(null).lessOrEqualTo(1L)  = true
     *   NumberUtils.fromNullable(1L).lessOrEqualTo(2L)  = true
     * </pre>
     *
     */
    public abstract boolean lessOrEqualTo(final Long number);
    
    /**
     * 两数比较 大于 >
     * <pre>
     *   NumberUtils.fromNullable(null).greaterThan(0) = false
     *   NumberUtils.fromNullable(null).greaterThan(0)   = false
     *   NumberUtils.fromNullable(0).greaterThan(null)  = false
     *   NumberUtils.fromNullable(0).greaterThan(0)  = false
     *   NumberUtils.fromNullable(1).greaterThan(null)  = true
     *   NumberUtils.fromNullable(null).greaterThan(1)  = false
     *   NumberUtils.fromNullable(1).greaterThan(2) = false
     * </pre>
     *
     */
    public abstract boolean greaterThan(final Integer number);
    
    /**
     * 两数比较 大于 >
     * <pre>
     *   NumberUtils.fromNullable(null).greaterThan(0L) = false
     *   NumberUtils.fromNullable(null).greaterThan(0L)   = false
     *   NumberUtils.fromNullable(0L).greaterThan(null)  = false
     *   NumberUtils.fromNullable(0L).greaterThan(0L)  = false
     *   NumberUtils.fromNullable(1L).greaterThan(null)  = true
     *   NumberUtils.fromNullable(null).greaterThan(1L)  = false
     *   NumberUtils.fromNullable(1L).greaterThan(2L) = false
     * </pre>
     *
     */
    public abstract boolean greaterThan(final Long number);
    
    /**
     * 两数比较 大于等于 >=
     * <pre>
     * 	NumberUtils.fromNullable(null).greaterThanOrEqualTo(null) = true
     * 	NumberUtils.fromNullable(null).greaterThanOrEqualTo(0) = true
     *  NumberUtils.fromNullable(0).greaterThanOrEqualTo(null) = true
     *  NumberUtils.fromNullable(0).greaterThanOrEqualTo(0) = true
     *  NumberUtils.fromNullable(1).greaterThanOrEqualTo(null) = true
     *  NumberUtils.fromNullable(null).greaterThanOrEqualTo(1) = flase
     *  NumberUtils.fromNullable(1).greaterThanOrEqualTo(2) = flase
     * </pre>
     *
     */
    public abstract boolean greaterThanOrEqualTo(Integer number);
    
    /**
     * 两数比较 大于等于 >=
     * <pre>
     * 	NumberUtils.fromNullable(null).greaterThanOrEqualTo(null) = true
     * 	NumberUtils.fromNullable(null).greaterThanOrEqualTo(0L) = true
     *  NumberUtils.fromNullable(0L).greaterThanOrEqualTo(null) = true
     *  NumberUtils.fromNullable(0L).greaterThanOrEqualTo(0L) = true
     *  NumberUtils.fromNullable(1L).greaterThanOrEqualTo(null) = true
     *  NumberUtils.fromNullable(null).greaterThanOrEqualTo(1L) = flase
     *  NumberUtils.fromNullable(1L).greaterThanOrEqualTo(2L) = flase
     * </pre>
     *
     */
    public abstract boolean greaterThanOrEqualTo(Long number);
    
    /**
     * 两数相减
     * <pre>
     * 	NumberUtils.fromNullable(null).subtract(null) = 0
     * 	NumberUtils.fromNullable(null).subtract(0) = 0
     *  NumberUtils.fromNullable(0).subtract(null) = 0
     *  NumberUtils.fromNullable(0).subtract(0) = 0
     *  NumberUtils.fromNullable(1).subtract(null) = 1
     *  NumberUtils.fromNullable(null).subtract(1) = -1
     *  NumberUtils.fromNullable(1).subtract(2) = -1
     * </pre>
     *
     */
    public abstract long subtract(Integer number);
    
    /**
     * 两数相减
     * <pre>
     * 	NumberUtils.fromNullable(null).subtract(null) = 0
     * 	NumberUtils.fromNullable(null).subtract(0L) = 0
     *  NumberUtils.fromNullable(0L).subtract(null) = 0
     *  NumberUtils.fromNullable(0L).subtract(0L) = 0
     *  NumberUtils.fromNullable(1L).subtract(null) = 1
     *  NumberUtils.fromNullable(null).subtract(1L) = -1
     *  NumberUtils.fromNullable(1L).subtract(2L) = -1
     * </pre>
     *
     */
    public abstract long subtract(Long number);
    
    /**
     * long转double
     * @return
     */
    public abstract double toDouble();
    
    /**
     * int to long
     * @return
     */
    public abstract long toLong();
    
    public static long toLong(String str) {
        return toLong(str, 0L);
    }
    
    public static long toLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
    
    public static NumberOperate fromNullable(Integer number) {
        return (number == null) ? new NumberOperate(0L) : new NumberOperate(toLong(number + ""));
    }
    
    public static NumberOperate fromNullable(Long number) {
        return (number == null) ? new NumberOperate(0L) : new NumberOperate(number);
    }
    
}
