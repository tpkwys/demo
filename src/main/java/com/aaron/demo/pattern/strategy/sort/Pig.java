package com.aaron.demo.pattern.strategy.sort;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Pig {
    int weight;
    public Pig(int weight){
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "weight=" + weight +
                '}';
    }
}
