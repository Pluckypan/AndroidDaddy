package com.echo.camera;

import android.util.Log;

import java.util.Locale;

import engineer.echo.hugox.DebugLog;

public class Person {
    private int age;
    private int gender;
    private String name;

    public Person() {
    }

    public Person(int age, int gender, String name) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    @Override
    public String toString() {
        return format(name, age, gender);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @DebugLog
    public String format(String name, int age, int gender) {
        String result = String.format(Locale.CHINA, "姓名:%s 年龄:%d 性别:%s", name, age, gender == 0 ? "男" : "女");
        Log.i("aop", "inner result=" + result);
        return result;
    }

    @Anno
    public int add(int a, int b) {
        return a + b;
    }

    @Anno(aTag = "PY-AOP", modify = true)
    public int minus(int a, int b) {
        return a - b;
    }
}
