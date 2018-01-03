package com.kbs.practical12;

/**
 * Created by Nimesh on 20-01-2017.
 */

public class User {




    int id;
    String name;
    int age;

    User()
    {

    }
    User(int uid,String uname,int uage)
    {
        this.id=uid;
        this.name=uname;
        this.age=uage;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
