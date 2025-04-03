package com.app.login.entity;

public class HistoryInfo {
    private int history_id;
    private String username;
    private int exercise_img;
    private String exercise_title;
    private int exercise_calories;
    private int exercise_count;
    private String date;
    private String comment;

    public HistoryInfo(int history_id, String username, int exercise_img, String exercise_title, int exercise_calories, int exercise_count, String date, String comment) {
        this.history_id = history_id;
        this.username = username;
        this.exercise_img = exercise_img;
        this.exercise_title = exercise_title;
        this.exercise_calories = exercise_calories;
        this.exercise_count = exercise_count;
        this.date = date;
        this.comment = comment;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getExercise_count() {
        return exercise_count;
    }

    public void setExercise_count(int exercise_count) {
        this.exercise_count = exercise_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
