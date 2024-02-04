package com.example.a2340project1.ui.toDo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElementHandler;

public class ToDoElementHandler extends DynamicElementHandler {
    /**
     * Dynamically adds a view from a resource to a destination layout in a fragment. View count
     * can be used to keep track of added views for repopulating fragments. Sets a long click
     * listener to create a pop-up to edit or delete tasks.
     *
     * @param viewGroup the view group destination in the corresponding fragment
     * @param inflater the inflater of the destination fragment
     * @param task the task being added
     * @param context the context of the destination fragment
     */
    public void addView(ViewGroup viewGroup, LayoutInflater inflater, ToDoElement task, Context context) {

        View addedView = inflater.inflate(task.getMainResource(), null, false);

        EditText taskText = addedView.findViewById(R.id.todo_edittext);

        ImageButton imageEdit = addedView.findViewById(task.getCloseButtonId());

        taskText.setText(task.getTaskText());
        taskText.setEnabled(false);

        imageEdit.setOnClickListener(v -> {
            taskEditDialog("Edit Task", viewGroup, inflater, addedView, context);//add later
        });

        viewGroup.addView(addedView);
    }

    /**
     * A version of the superclass showEditDialog method that references the to-do
     * fragment related fields.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)
     */
    public void taskEditDialog(String title, ViewGroup viewGroup, LayoutInflater inflater,
                               View view, Context context) {
        super.showEditDialog(title, viewGroup, inflater, view, context, R.layout.todo_dialogue,
                R.id.dialogue_edit_text, R.id.todo_edittext);
    }
}

