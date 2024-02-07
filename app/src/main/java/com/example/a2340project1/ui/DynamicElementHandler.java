package com.example.a2340project1.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a2340project1.R;

/**
 * Handles adding, removing, and editing dynamically added views. Adding is
 * implemented through a method called in the destination fragment. Editing and
 * deleting are implemented through a dialog pop-up method called in the destination
 * fragment.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class DynamicElementHandler {
    /**
     * Dynamically adds a view from a resource to a destination layout in a fragment. View count
     * can be used to keep track of added views for repopulating fragments.
     *
     * @param viewGroup the view group destination in the corresponding fragment
     * @param inflater the inflater of the destination fragment
     * @param element the element being added
     */
    public void addView(ViewGroup viewGroup, LayoutInflater inflater, DynamicElement element) {

        View addedView = inflater.inflate(element.getMainResource(), null, false);

        viewGroup.addView(addedView);
    }

    /**
     * Creates a dialog window for the user to edit an element's text. Also allows user to
     * delete the selected element.
     *
     * @param title title of the dialog window
     * @param viewGroup the view group of the selected element
     * @param inflater the inflater of the current fragment
     * @param view the view to be changed/deleted
     * @param context the destination context
     * @param dialogLayout id of the custom dialog layout
     * @param editText id of the edit input box in the dialog
     * @param changedText id of the changed text box
     */
    public void showEditDialog(String title, ViewGroup viewGroup, LayoutInflater inflater,
                               View view, Context context, int dialogLayout, int editText,
                               int changedText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogBoxTheme);
        builder.setTitle(title);

        // set the custom layout
        View customLayout = inflater.inflate(dialogLayout, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText dialogEdit = customLayout.findViewById(editText);
            EditText taskText = view.findViewById(changedText);
            String newText = dialogEdit.getText().toString();
            if (!newText.equals("")) {
                taskText.setText(newText);
            }
        });
        builder.setNeutralButton("Delete", (dialog, which) -> viewGroup.removeView(view));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Checks if any of the input fields in the dialog are empty.
     *
     * @param inputs the text boxes where inputs are received
     * @return boolean representing whether any of the inputs are empty
     */
    public boolean nonEmptyDialog(EditText... inputs) {
        boolean isEmpty = false;
        for (EditText input : inputs) {
            if (input.getText().toString().equals("")) {
                isEmpty = true;
            }
        }
        return !isEmpty;
    }
}
