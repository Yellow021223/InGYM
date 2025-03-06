package com.app.login.entity;

import java.io.Serializable;

public class ExerciseInfo implements Serializable {

    private int exercise_id;
    private int product_img;
    private String exercise_title;
    private String exercise_details;
    private int exercise_calories;




    public ExerciseInfo(int exercise_id, int product_img, String exercise_title, String exercise_details,int exercise_calories) {
        this.exercise_id = exercise_id;
        this.product_img = product_img;
        this.exercise_title = exercise_title;
        this.exercise_details = exercise_details;
        this.exercise_calories = exercise_calories;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getProduct_img() {
        return product_img;
    }

    public void setProduct_img(int product_img) {
        this.product_img = product_img;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public void setExercise_title(String exercise_title) {
        this.exercise_title = exercise_title;
    }

    public String getExercise_details() {
        return exercise_details;
    }

    public void setExercise_details(String exercise_details) {
        this.exercise_details = exercise_details;
    }

    public int getExercise_calories() {
        return exercise_calories;
    }

    public void setExercise_calories(int exercise_calories) {
        this.exercise_calories = exercise_calories;
    }
}
