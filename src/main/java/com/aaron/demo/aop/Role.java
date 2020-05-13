package com.aaron.demo.aop;

/**
 * @program: demo
 * @description: 角色类
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class Role {
    private String id;
    private String name;
    private String note;

    public Role(String id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
