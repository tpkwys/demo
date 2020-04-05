package com.aaron.demo.algorithm;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-04
 **/

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 */
public class CheckPermutation {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        CheckPermutation demo=new CheckPermutation();
        while(scanner.hasNextLine()){
            String s1=scanner.nextLine();
            String s2=scanner.nextLine();
            System.out.println(demo.solution(s1,s2));
        }
    }
    public boolean solution(String s1,String s2){
        if(s1==null||s2==null||s1.length()!=s2.length()){
            return false;
        }
        char[] s1Chars=s1.toCharArray();
        char[] s2Chars=s2.toCharArray();
        Arrays.sort(s1Chars);
        Arrays.sort(s2Chars);
        int length=s1.length();
        for(int i=0;i<length;i++){
            if(s1Chars[i]!=s2Chars[i]){
                return false;
            }
        }
        return true;
    }
}
