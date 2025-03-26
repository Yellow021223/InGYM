package com.app.login.entity;

public class PlanInfo {
    private int plan_id;
    private String username;
    private int exercise_id;
    private int exercise_img;
    private String exercise_title;
    private int exercise_calories;
    private int exercise_count;


    public PlanInfo(int plan_id, String username, int exercise_id, int exercise_img, String exercise_title, int exercise_calories, int exercise_count) {
        this.plan_id = plan_id;
        this.username = username;
        this.exercise_id = exercise_id;
        this.exercise_img = exercise_img;
        this.exercise_title = exercise_title;
        this.exercise_calories = exercise_calories;
        this.exercise_count = exercise_count;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public int getExercise_count() {
        return exercise_count;
    }

    public void setExercise_count(int exercise_count) {
        this.exercise_count = exercise_count;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getExercise_img() {
        return exercise_img;
    }

    public void setExercise_img(int exercise_img) {
        this.exercise_img = exercise_img;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public void setExercise_title(String exercise_title) {
        this.exercise_title = exercise_title;
    }

    public int getExercise_calories() {
        return exercise_calories;
    }

    public void setExercise_calories(int exercise_calories) {
        this.exercise_calories = exercise_calories;
    }
}
