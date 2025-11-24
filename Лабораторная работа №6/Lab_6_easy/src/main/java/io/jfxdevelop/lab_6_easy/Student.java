package io.jfxdevelop.lab_6_easy;

public class Student {
    private int id;
    private String name;
    private int age;
    private String group;

    public Student(int id, String name, int age, String group) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGroup() { return group; }
}
