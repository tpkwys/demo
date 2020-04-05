package com.aaron.demo.algorithm;

import java.util.Scanner;

/**
 * @program: demo
 * @description: 将空格替换成%20
 * @author: tianpanke
 * @create: 2020-04-04
 **/

/**
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *
 * 示例1:
 *
 *  输入："Mr John Smith    ", 13
 *  输出："Mr%20John%20Smith"
 * 示例2:
 *
 *  输入："               ", 5
 *  输出："%20%20%20%20%20"
 * 提示：
 *
 * 字符串长度在[0, 500000]范围内。
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ReplaceSpace demo=new ReplaceSpace();
        while(scanner.hasNextLine()){
            String s=scanner.nextLine();
            int length=scanner.nextInt();
            System.out.println(demo.solution1(s,length));
            System.out.println(demo.solution2(s,length));
        }
    }
    public String solution1(String s1,int length){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            if(s1.charAt(i)==' '){
                sb.append("%20");
            }else{
                sb.append(s1.charAt(i));
            }
        }
        return sb.toString();
    }

    public String solution2(String s1,int length){
        return s1.substring(0,length).replaceAll(" ","%20");
    }
}
