package domain;

/**
 * User
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
public class User {

    private String id;
    private String name;
    private String surName;

    public User() {
    }

    public User(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public User(String id, String name, String surName) {
        this(name, surName);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

}
