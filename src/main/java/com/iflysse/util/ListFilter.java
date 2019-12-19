package com.iflysse.util;

import java.util.ArrayList;
import java.util.List;

public class ListFilter<T, O> {
    public List<O> Filter(List<T> source, Condition<T> condition, Converter<T,O> converter){
        if(source==null){
            return new ArrayList<O>(0);
        }
        List<O> result = new ArrayList<>();
        for(T item:source){
            if (condition.getCondition(item)){
                result.add(converter.Convert(item));
            }
        }
        return result;
    }
}
