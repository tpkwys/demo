package com.aaron.demo.generic;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-06
 **/
public class Apple {
    private String brand;
    private String weight;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "brand='" + brand + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
