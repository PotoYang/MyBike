package com.example.testhelper;

/**
 * Created by 71579 on 2016/11/24.
 */

public class Movie {
    private String name;
    private String rating;
    private String image;
    private String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Movie() {
    }

    public Movie(String name, String rating, String imageUrl, String year) {
        this.name = name;
        this.rating = rating;
        this.image = imageUrl;
        this.year = year;
    }


}
