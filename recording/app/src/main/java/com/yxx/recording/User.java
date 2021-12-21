package com.yxx.recording;

public class User {
    private String name;
    private String age;
    private String sex;
  private String table_name;

    public User(String name, String age, String sex, String table_name) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.table_name = table_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
}
