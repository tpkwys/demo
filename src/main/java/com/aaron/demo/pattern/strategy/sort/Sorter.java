package com.aaron.demo.pattern.strategy.sort;

import com.aaron.demo.pattern.strategy.sort.MyComparable;
import com.aaron.demo.pattern.strategy.sort.MyComparator;

/**
 * @program: demo
 * @description: 排序
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Sorter {

    public static<T> void sort(T[]array, MyComparator<T> comparator){
        for(int i=0;i<array.length-1;i++){
            int minPos=i;
            for(int j=i+1;j<array.length;j++){
                minPos=comparator.compare(array[minPos],array[j])>0?j:minPos;
            }
            swap(array,i,minPos);
        }
    }
    public static <T> void swap(T[]array,int i,int j){
        T temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static void sort(MyComparable[] array){
        for(int i=0;i<array.length-1;i++){
            int minPos=i;
            for(int j=i+1;j<array.length;j++){
                minPos=array[minPos].compareTo(array[j])>0?j:minPos;
            }
            swap(array,i,minPos);
        }
    }
    static void swap(MyComparable[]array,int i,int j){
        MyComparable temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
}
