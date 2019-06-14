package com.tomato.framework.core.util;

import com.google.common.base.Optional;
import com.google.common.collect.BoundType;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * number 操作final 类
 *
 * @author yuanguohua
 */
public final class NumberOperate extends NumberUtils {
    
    private final Long longReference;
    
    NumberOperate(Integer intReference) {
        this.longReference = toLong(String.valueOf(intReference));
    }
    
    NumberOperate(Long longReference) {
        this.longReference = longReference;
    }
    
    @Override
    public boolean rang(Integer number1, Integer number2) {
        return rang(Optional.fromNullable(number1).or(0).longValue(), Optional.fromNullable(number2).or(0).longValue());
    }
    
    @Override
    public boolean rang(Long number1, Long number2) {
        return Range.open(number1, number2).contains(longReference);
    }
    
    @Override
    public boolean rang(Integer number1, boolean isContainsLeft, Integer number2, boolean isContainsRight) {
        return rang(Optional.fromNullable(number1).or(0).longValue(), isContainsLeft,
            Optional.fromNullable(number2).or(0).longValue(), isContainsRight);
    }
    
    @Override
    public boolean rang(Long number1, boolean isContainsLeft, Long number2, boolean isContainsRight) {
        BoundType lowerType = BoundType.OPEN;
        BoundType upperType = BoundType.OPEN;
        if (isContainsLeft) {
            lowerType = BoundType.CLOSED;
        }
        if (isContainsRight) {
            upperType = BoundType.CLOSED;
        }
        return Range.range(number1, lowerType, number2, upperType).contains(longReference);
    }
    
    @Override
    public boolean isEqual(Integer number) {
        // Long1,
        // Long2比较值相等，用equal，在Long=128之前用==是没问题的，但是在128之后，==就会为false
        return isEqual(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public boolean isEqual(Long number) {
        return Optional.fromNullable(longReference).or(0L).equals(Optional.fromNullable(number).or(0L));
    }
    
    @Override
    public boolean contains(Integer... numbers) {
        List<Long> nums = Lists.newArrayList(numbers).stream()
            .map(number -> Optional.fromNullable(number).or(0).longValue()).collect(Collectors.toList());
        return contains(nums.stream().toArray(Long[]::new));
    }
    
    @Override
    public boolean contains(Long... numbers) {
        if (EmptyUtils.isEmpty(numbers)) {
            return false;
        }
        List<Long> nums = Arrays.asList(numbers);
        return nums.contains(longReference);
    }
    
    @Override
    public long subtract(Integer number) {
        return subtract(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public long subtract(Long number) {
        return Optional.fromNullable(longReference).or(0L) - Optional.fromNullable(number).or(0L);
    }
    
    @Override
    public boolean greaterThanOrEqualTo(Integer number) {
        return greaterThanOrEqualTo(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public boolean greaterThanOrEqualTo(Long number) {
        return Optional.fromNullable(longReference).or(0L) >= Optional.fromNullable(number).or(0L);
    }
    
    @Override
    public boolean greaterThan(Integer number) {
        return greaterThan(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public boolean greaterThan(Long number) {
        return Optional.fromNullable(longReference).or(0L) > Optional.fromNullable(number).or(0L);
    }
    
    @Override
    public boolean lessOrEqualTo(Integer number) {
        return lessOrEqualTo(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public boolean lessOrEqualTo(Long number) {
        return Optional.fromNullable(longReference).or(0L) <= Optional.fromNullable(number).or(0L);
    }
    
    @Override
    public boolean less(Integer number) {
        return less(Optional.fromNullable(number).or(0).longValue());
    }
    
    @Override
    public boolean less(Long number) {
        return Optional.fromNullable(longReference).or(0L) < Optional.fromNullable(number).or(0L);
    }
    
    @Override
    public double toDouble() {
        return Optional.fromNullable(longReference).or(0L).doubleValue();
    }
    
    @Override
    public long toLong() {
        return Optional.fromNullable(longReference).or(0L).longValue();
    }
}
