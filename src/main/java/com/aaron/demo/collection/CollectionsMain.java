package com.aaron.demo.collection;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-28
 **/
public class CollectionsMain {
    public static void main(String[] args) {
        List<User> userList=init();
        System.out.println(JSON.toJSON(userList));
        Collections.reverse(userList);
        System.out.println(JSON.toJSON(userList));
        Collections.shuffle(userList);
        System.out.println(JSON.toJSON(userList));
        //定制排序
//        Collections.sort(userList, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getAge()>o2.getAge();
//            }
//        });

    }
    private static List<User> init(){
        List<User> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            User user=new User();
            user.setName("name"+i);
            user.setAge(i);
            list.add(user);
        }
        return list;
    }
}
