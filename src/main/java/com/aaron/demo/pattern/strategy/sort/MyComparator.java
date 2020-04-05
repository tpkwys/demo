package com.aaron.demo.pattern.strategy.sort;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public interface MyComparator<T>{
    int compare(T o1,T o2);
}
