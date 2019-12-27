package com.example.note_project;


public class Note {

    private String id ;
    private String title ;
    private String note;
    private String date;


    public Note(String id, String title, String note, String date) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
