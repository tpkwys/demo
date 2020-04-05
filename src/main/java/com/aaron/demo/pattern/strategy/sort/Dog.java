package com.aaron.demo.pattern.strategy.sort;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Dog implements MyComparable<Dog> {
    int weight;
    int height;
    Dog(int weight,int height){
        this.weight=weight;
        this.height=height;
    }
    @Override
    public int compareTo(Dog d) {
        if(this.weight>d.weight){
            return 1;
        }else if(this.weight<d.weight){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
