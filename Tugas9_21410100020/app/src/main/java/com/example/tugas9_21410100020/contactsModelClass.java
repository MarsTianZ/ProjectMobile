package com.example.tugas9_21410100020;

public class contactsModelClass {

    Integer id;
    String name;
    String noHP;

    public contactsModelClass(String name, String noHP) {
        this.name = name;
        this.noHP = noHP;
    }

    public contactsModelClass(Integer id, String name, String noHP) {
        this.id = id;
        this.name = name;
        this.noHP = noHP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getnoHP() {
        return noHP;
    }

    public void setnoHP(String noHP) {
        this.noHP = noHP;
    }
}
