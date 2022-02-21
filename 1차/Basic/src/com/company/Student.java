package com.company;

public class Student extends AbstractPerson{

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void speak(){
        super.speak();
        System.out.println(" And also i'm student");
    }

}
