package com.company;

public abstract class AbstractPerson implements Person{
    protected String name;
    protected int age;

    @Override
    public void speak() {
        System.out.print("My name is "+name+" And my " +
                "age is "+age);
    }
}
