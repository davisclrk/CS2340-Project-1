package com.example.a2340project1.ui.classes;

import com.example.a2340project1.ui.DynamicElement;

import java.util.ArrayList;

public class ClassElement extends DynamicElement {
    private String className;
    private String classDate;//make this formatted to date
    private String instructor;
    private ArrayList<Integer> daysChecked;
    private int hour;
    private int minute;
    public ClassElement(int mainResource, String className,
                        String classDate, String instructor,
                        ArrayList<Integer> daysChecked, int hour, int minute) {
        super(mainResource);
        this.className = className;
        this.classDate = classDate;
        this.instructor = instructor;
        this.daysChecked = daysChecked;
        this.hour = hour;
        this.minute = minute;
    }

    public String getClassName() {
        return className;
    }

    public String getClassDate() {
        return classDate;
    }

    public String getInstructor() {
        return instructor;
    }

    public ArrayList<Integer> getDaysChecked() {return daysChecked;}

    public int getHour() {return hour;}

    public int getMinute() {return minute;}

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
