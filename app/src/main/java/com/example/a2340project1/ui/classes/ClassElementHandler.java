package com.example.a2340project1.ui.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElement;
import com.example.a2340project1.ui.DynamicElementHandler;

public class ClassElementHandler extends DynamicElementHandler {
    /**
     * A version of the superclass method of the same name. Adds a class element to the
     * context.
     *
     * @see DynamicElementHandler#addView(ViewGroup, LayoutInflater, DynamicElement)
     */
    public void addView(ViewGroup viewGroup, LayoutInflater inflater, ClassElement addedClass,
                        Context context) {

        View addedView = inflater.inflate(addedClass.getMainResource(), null, false);
        EditText className = addedView.findViewById(R.id.class_name);
        EditText classDate = addedView.findViewById(R.id.class_time);
        EditText classInstructor = addedView.findViewById(R.id.class_instructor);

        className.setEnabled(false);
        className.setText(addedClass.getClassName());
        classDate.setEnabled(false);
        classDate.setText(addedClass.getClassDate());
        classInstructor.setEnabled(false);
        classInstructor.setText(addedClass.getInstructor());

        ImageButton classEditButton = addedView.findViewById(R.id.edit_class);
        classEditButton.setOnClickListener(view1 -> classEditDialog(viewGroup,
                inflater, addedView, context));

        viewGroup.addView(addedView);
        viewCount++;
    }

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields. Also adds a new class to the context.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)  
     */
    public void classAddDialog(ViewGroup viewGroup, LayoutInflater inflater, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Class");

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.class_create_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText classNameAdd = customLayout.findViewById(R.id.add_class_name);
            EditText classInstructorAdd = customLayout.findViewById(R.id.add_class_instructor);
            RadioGroup dayCheck = customLayout.findViewById(R.id.class_days);
            TimePicker timePicker = customLayout.findViewById(R.id.timePicker);

            String nameText, dateText, instructorText;

            if (nonEmptyAddDialog(classNameAdd, classInstructorAdd)) {
                nameText = classNameAdd.getText().toString();
                dateText = getClassDateFromDialog(dayCheck, timePicker);
                instructorText = classInstructorAdd.getText().toString();
                ClassElement newClass = new ClassElement(R.layout.class_grid,
                        nameText, dateText, instructorText);

                addView(viewGroup, inflater, newClass, context);
            }

        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int) 
     */
    public void classEditDialog(ViewGroup viewGroup, LayoutInflater inflater,
                               View view, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Class");

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.class_create_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText classNameEdit = customLayout.findViewById(R.id.add_class_name);
            EditText classInstructorEdit = customLayout.findViewById(R.id.add_class_instructor);
            RadioGroup dayCheckEdit = customLayout.findViewById(R.id.class_days);
            TimePicker timePickerEdit = customLayout.findViewById(R.id.timePicker);

            EditText className = view.findViewById(R.id.class_name);
            EditText classDate = view.findViewById(R.id.class_time);
            EditText classInstructor = view.findViewById(R.id.class_instructor);

            //set editing window to have same inputs as the selected view
            classNameEdit.setText(className.getText());
            classInstructorEdit.setText(classInstructor.getText());

            String nameText, dateText, instructorText;

            //add empty check for date/time
            if (nonEmptyAddDialog(classNameEdit, classInstructorEdit)) {
                nameText = classNameEdit.getText().toString();
                dateText = getClassDateFromDialog(dayCheckEdit, timePickerEdit);
                instructorText = classInstructorEdit.getText().toString();

                className.setText(nameText);
                classDate.setText(dateText);
                classInstructor.setText(instructorText);
            }

        });
        builder.setNeutralButton("Delete", (dialog, which) -> viewGroup.removeView(view));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Gets a string version of the chosen days and time from the add class dialog.
     *
     * @return a string version of the class days and time
     */
    public String getClassDateFromDialog(RadioGroup radioGroup, TimePicker timePicker) {
        String time;
        String hour = (timePicker.getHour() > 12) ?
                String.valueOf(timePicker.getHour() - 12) : String.valueOf(timePicker.getHour());
        String minute = (timePicker.getMinute() < 10) ?
                "0" + timePicker.getMinute() : String.valueOf(timePicker.getMinute());
        String AMPM = (timePicker.getHour() < 12) ? "AM" : "PM";
        time = hour + ":" + minute + " " + AMPM;
        int count = radioGroup.getChildCount();
        String days = "";
        for (int i = 0; i < count; i++) {
            View v = radioGroup.getChildAt(i);
            if (v instanceof CheckBox && ((CheckBox) v).isChecked()) {
                CheckBox selectedAnswer = (CheckBox) v;
                days += selectedAnswer.getText().toString() + ", ";
            }
        }
        return days + time;
    }

    /**
     * Checks if any of the input fields in the dialog are empty.
     *
     * @param inputs the text boxes where inputs are received
     * @return boolean representing whether any of the inputs are empty
     */
    private boolean nonEmptyAddDialog(EditText... inputs) {
        boolean isEmpty = false;
        for (EditText input : inputs) {
            if (input.getText().toString().equals("")) {
                isEmpty = true;
            }
        }
        return !isEmpty;
    }
}
