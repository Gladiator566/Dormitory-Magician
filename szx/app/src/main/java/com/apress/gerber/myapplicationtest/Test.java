package com.apress.gerber.myapplicationtest;

/**
 * Created by Administrator on 2016/9/27.
 */
public class Test {
    private String name;
    private  int imageID;
    public Test(String name, int imageID){
        this.imageID = imageID;
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public int getImageID(){
        return  imageID;
    }
}
