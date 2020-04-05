package com.aaron.demo.algorithm;

/**
 * @program: demo
 * @description: 字符唯一判断
 * @author: tianpanke
 * @create: 2020-04-04
 **/

import java.util.Scanner;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class CharIsUnique {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        CharIsUnique unique=new CharIsUnique();
        while(scanner.hasNextLine()){
            String s=scanner.nextLine();
            System.out.println(unique.solution(s));
            System.out.println(unique.solution1(s));
        }
    }
    //双重遍历
    public boolean solution(String astr){
        if(astr==null||astr.length()<2){
            return true;
        }
        char[]chars=astr.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c=chars[i];
            for(int j=i+1;j<chars.length;j++){
                if(chars[j]==c){
                    return false;
                }
            }
        }
        return  true;
    }
    //ASCII值标记法，不支持中文
    public boolean solution1(String astr){
        int []temp=new int[256];
        for(int i=0;i<astr.length();i++){
            char c=astr.charAt(i);
            if(temp[c]==1){
                return false;
            }
            temp[c]=1;
        }
        return true;
    }
}
