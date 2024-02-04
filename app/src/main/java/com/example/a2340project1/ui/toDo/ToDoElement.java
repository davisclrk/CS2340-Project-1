package com.example.a2340project1.ui.toDo;

import com.example.a2340project1.ui.DynamicElement;

public class ToDoElement extends DynamicElement {
    private int closeButtonId;
    private String taskText;
    public ToDoElement(int mainResource, int closeButtonId, String taskText) {
        super(mainResource);
        this.closeButtonId = closeButtonId;
        this.taskText = taskText;
    }

    public int getCloseButtonId() {
        return closeButtonId;
    }

    public String getTaskText() {
        return taskText;
    }
}
