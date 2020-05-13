package com.aaron.demo.generic;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-06
 **/
public abstract class Subscrib<T> {

    public T toObject(String s){
        Type[] type=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
        return JSON.parseObject(s,type[0]);
    }
}
