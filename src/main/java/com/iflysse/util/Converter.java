package com.iflysse.util;

/**
 * 类型转换
 * @param <I> 输入实体
 * @param <O> 输出实体
 */
public interface Converter<I,O> {
    /**
     * 转换对象
     * @param input
     * @return
     */
    public O Convert(I input);
}
