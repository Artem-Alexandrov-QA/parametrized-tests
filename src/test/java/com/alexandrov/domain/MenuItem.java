package com.alexandrov.domain;

public enum MenuItem {

    MANS("Мужчинам"),
    CHILDREN("Детям");

    private String desc;

    MenuItem(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
