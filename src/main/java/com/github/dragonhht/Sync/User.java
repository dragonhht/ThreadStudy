package com.github.dragonhht.Sync;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-2-28
 */
public class User {

    private String name;
    private String id;
    private int age;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public synchronized void addIndex(int num) throws InterruptedException {
        System.out.println("add start");
        int temp = getIndex();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            temp += num;
            setIndex(temp);
        }
        System.out.println("add: " + getIndex());
    }

    public synchronized void minusIndex(int num) throws InterruptedException {
        System.out.println("minus start");
        int temp = getIndex();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            temp -= num;
            setIndex(temp);
        }
        System.out.println("minus: " + getIndex());
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                ", index=" + index +
                '}';
    }
}
