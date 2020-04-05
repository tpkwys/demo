package com.aaron.demo.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: demo
 * @description: 重排后回文串判断
 * @author: tianpanke
 * @create: 2020-04-04
 **/

/**
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *
 * 回文串不一定是字典当中的单词。
 *
 *  
 *
 * 示例1：
 *
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 */
//核心思想：统计字符串中 个数为奇数的字符个数。大于1 则无法构成回文；小于等于1 则可以构成
public class CanPermutePalindrome {
    public static void main(String[] args) {
        CanPermutePalindrome demo=new CanPermutePalindrome();
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            String s=scanner.nextLine();
            System.out.println(demo.solution(s));
            System.out.println(demo.solution1(s));
        }

    }

    public boolean solution1(String s){
        if(s==null||s.length()==0){
            return false;
        }
        HashSet<Character> characters=new HashSet<>();
        for(int i=0;i<s.length();i++){
            Character c=s.charAt(i);
            if(characters.add(c)){ //之前没有该字符
               //do nothing
            }else{//之前有该字符
                characters.remove(c);
            }
        }
        return  characters.size()<=1;
    }
    public boolean solution(String s){
        if(s==null||s.length()==0){
            return false;
        }
        HashMap<Integer,Integer> countMap=new HashMap<>();
        for(int j=0;j<s.length();j++){
            char c=s.charAt(j);
            Integer i=(int) c;
            if(countMap.containsKey(i)){
                countMap.put(i,countMap.get(i)+1);
            }else{
                countMap.put(i,1);
            }
        }

        int z=0;
        for(Map.Entry<Integer,Integer> entry:countMap.entrySet()){
            if(entry.getValue()%2==1){
                z++;
            }
            if(z>1){
                return false;
            }
        }

        return true;

    }
}
