package com.full1.finaljecheon;

/**
 * Created by Full1 on 2017-07-31.
 */

public class EventField {
    String table;
    String title;
    String name;

    String place;
    String content;
    String time;
    String exc;

    public String getExc() {return exc;}

    public void setExc(String exc) {this.exc = exc;}

    public EventField(String table, String title, String name, String place, String content, String time, String exc) {
        this.table = table;
        this.title = title;
        this.name = name;
        this.place = place;
        this.content = content;
        this.time = time;
        this.exc = exc;
    }
    public String getTable() {return table;}
    public String getTitle() {return title;}
    public String getName() {return name;}
    public String getPlace() {return place;}
    public String getContent() {return content;}
    public String getTime() {return time;}

    public void setTable(String table) {this.table = table;}
    public void setTitle(String title) {this.title = title;}
    public void setName(String name) {this.name = name;}
    public void setPlace(String place) {this.place = place;}
    public void setContent(String content) {this.content = content;}
    public void setTime(String time) {this.time = time;}
}
