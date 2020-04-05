package com.aaron.demo.pattern.strategy.sort;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Cat implements MyComparable<Cat> {
    int weight,height;
    public Cat(int weight,int height){
        this.weight=weight;
        this.height=height;
    }

    @Override
    public int compareTo(Cat c) {
        if(this.weight<c.weight){
            return -1;
        }else if(this.weight>c.weight){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
