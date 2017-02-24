package com.example.dormitory_magician;


class LifeCycle {
    private String type;
    private int people;
    private String time;
    private String place;
    LifeCycle(String type, int people, String time, String place){
        this.type=type;
        this.people=people;
        this.time=time;
        this.place=place;
    }
    String getType(){
        return type;
    }
    int getPeople(){
        return people;
    }
    String getTime(){
        return time;
    }
    String getPlace(){
        return place;
    }
    public void setType(String type){
        this.type=type;
    }
    void setPeople(int people){
        this.people=people;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setPlace(String place){
        this.place=place;
    }
}
