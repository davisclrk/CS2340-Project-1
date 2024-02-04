package com.example.a2340project1.ui.toDo;

import com.example.a2340project1.ui.DynamicElement;
/**
 * Stores data about tasks dynamically added to the to-do fragment.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class ToDoElement extends DynamicElement {
    private String taskText;
    public ToDoElement(int mainResource, String taskText) {
        super(mainResource);
        this.taskText = taskText;
    }

    public String getTaskText() {
        return taskText;
    }
}
