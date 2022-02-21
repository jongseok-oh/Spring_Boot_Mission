package com.company;

public class Lecturer extends AbstractPerson{

    public Lecturer(String name, int age) {
        this.name =name;
        this.age = age;
    }

    @Override
    public void speak() {
        super.speak();

        System.out.println(" And also i'm lecture");
    }
}
