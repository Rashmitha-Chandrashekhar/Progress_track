package com.example.progresstrack;

public class addsubfield {
    String name,comment;

    public addsubfield() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public addsubfield(String name, String comment) {
        this.name = name;
        this.comment = comment;

    }
}
