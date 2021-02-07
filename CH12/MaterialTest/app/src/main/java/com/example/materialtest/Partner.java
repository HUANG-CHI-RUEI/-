package com.example.materialtest;

public class Partner {

    private String name;

    private int imageId;
    private int profileId;

    public Partner(String name, int imageId, int profileId) {
        this.name = name;
        this.imageId = imageId;
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getProfileId() {
        return profileId;
    }
}
