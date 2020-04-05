package com.aaron.demo.pattern.strategy.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Main {
    public static void main(String[] args) {
        Cat[]arr={new Cat(1,1),new Cat(3,3),new Cat(2,2)};
        Sorter.sort(arr);
        System.out.println(Arrays.toString(arr));
        Dog[]arr1={new Dog(1,1),new Dog(3,3),new Dog(2,2)};
        Sorter.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        Arrays.sort(arr, new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return 0;
            }
        });
        Pig[] pigs={new Pig(1),new Pig(8),new Pig(2)};
        Sorter.sort(pigs, new MyComparator<Pig>() {
            @Override
            public int compare(Pig o1, Pig o2) {
                if(o1.weight>o2.weight){
                    return 1;
                }else if(o1.weight<o2.weight){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        System.out.println(Arrays.toString(pigs));
    }
}