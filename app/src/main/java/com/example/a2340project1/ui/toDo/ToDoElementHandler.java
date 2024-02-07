package com.example.a2340project1.ui.toDo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElementHandler;
/**
 * Handles adding, removing, and editing dynamically added tasks to the
 * to-do fragment. Implementations of these actions are the same as in
 * DynamicElementHandler.
 *
 * @author aidannguyen
 * @version 1.0
 */
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
    public void todoAddView(ViewGroup viewGroup, LayoutInflater inflater, ToDoElement task, Context context) {

        View addedView = inflater.inflate(task.getMainResource(), null, false);

        EditText taskText = addedView.findViewById(R.id.todo_edittext);

        ImageButton imageEdit = addedView.findViewById(R.id.edit_task);

        taskText.setText(task.getTaskText());
        taskText.setEnabled(false);

        imageEdit.setOnClickListener(v -> {
            taskEditDialog("Edit Task", viewGroup, inflater, addedView, context);
        });

        viewGroup.addView(addedView);
    }

    /**
     * A version of the superclass showEditDialog method that references the to-do
     * fragment related fields.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)
     */
    private void taskEditDialog(String title, ViewGroup viewGroup, LayoutInflater inflater,
                               View view, Context context) {
        super.showEditDialog(title, viewGroup, inflater, view, context, R.layout.todo_dialog,
                R.id.dialogue_edit_text, R.id.todo_edittext);
    }
}

