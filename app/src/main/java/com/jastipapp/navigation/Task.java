package com.jastipapp.navigation;

public class Task implements Inflatable {
    private String name, picture, description;

    public Task(String name, String picture, String description) {
        this.name = name;
        this.picture = picture;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getItemText1() {
        return this.name;
    }

    @Override
    public String getItemText2() {
        return this.picture;
    }

    @Override
    public String getItemText3() {
        return this.description;
    }
}
