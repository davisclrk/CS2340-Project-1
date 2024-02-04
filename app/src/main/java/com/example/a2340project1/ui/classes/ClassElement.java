package com.example.a2340project1.ui.classes;

import com.example.a2340project1.ui.DynamicElement;

public class ClassElement extends DynamicElement {
    private String className;
    private String classDate;//make this formatted to date
    private String instructor;
    public ClassElement(int mainResource, String className,
                        String classDate, String instructor) {
        super(mainResource);
        this.className = className;
        this.classDate = classDate;
        this.instructor = instructor;
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
