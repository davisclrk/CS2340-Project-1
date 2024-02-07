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
import android.widget.Toast;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElement;
import com.example.a2340project1.ui.DynamicElementHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Handles adding, removing, and editing dynamically added classes to the
 * classes fragment. Implementations of these actions are the same as in
 * DynamicElementHandler.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class ClassElementHandler extends DynamicElementHandler {

    private static final ArrayList<String> classNames = new ArrayList<>();

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields. Also adds a new class to the context.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)  
     */
    public void classAddDialog(ViewGroup viewGroup, LayoutInflater inflater, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogBoxTheme);
        builder.setTitle("Add Class");

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.class_create_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            EditText classNameAdd = customLayout.findViewById(R.id.add_class_name);
            EditText classInstructorAdd = customLayout.findViewById(R.id.add_class_instructor);
            RadioGroup dayCheck = customLayout.findViewById(R.id.class_days);
            TimePicker timePicker = customLayout.findViewById(R.id.class_time_picker);

            String nameText, dateText, instructorText;
            ArrayList<Integer> daysChecked;
            int hour, minute;

            if (nonEmptyDialog(classNameAdd, classInstructorAdd)) {
                nameText = classNameAdd.getText().toString();
                dateText = getClassDateFromDialog(dayCheck, timePicker);
                instructorText = classInstructorAdd.getText().toString();
                daysChecked = getCheckedIndices(dayCheck);
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                // check for duplicate class names
                boolean classExists = false;
                for (String i:classNames) if (i.equals(nameText)) {
                    classExists = true;
                    break;
                }

                if (!classExists) {
                    ClassElement newClass = new ClassElement(R.layout.class_grid,
                            nameText, dateText, instructorText, daysChecked, hour, minute);

                    classAddView(viewGroup, inflater, newClass, context);
                    classNames.add(nameText);
                } else {
                    Toast.makeText(context, "Class already exists!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }

        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * A version of the superclass method of addView. Adds a class element to the
     * context.
     *
     * @see DynamicElementHandler#addView(ViewGroup, LayoutInflater, DynamicElement)
     */
    private void classAddView(ViewGroup viewGroup, LayoutInflater inflater, ClassElement addedClass,
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
                inflater, addedView, addedClass, context));

        viewGroup.addView(addedView);
    }

    /**
     * Finds and returns the indices of the checked elements in a radio group.
     *
     * @param checkedGroup a radio group of check boxes
     * @return an arraylist of the indices of the checked elements
     */
    private ArrayList<Integer> getCheckedIndices(RadioGroup checkedGroup) {
        int count = checkedGroup.getChildCount();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View v = checkedGroup.getChildAt(i);
            if (v instanceof CheckBox && ((CheckBox) v).isChecked()) {
                arr.add(i);
            }
        }
        return arr;
    }

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int) 
     */
    private void classEditDialog(ViewGroup viewGroup, LayoutInflater inflater,
                                View view, ClassElement editedClass, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogBoxTheme);
        builder.setTitle("Edit Class");

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.class_create_dialog, null);
        builder.setView(customLayout);

        EditText classNameEdit = customLayout.findViewById(R.id.add_class_name);
        EditText classInstructorEdit = customLayout.findViewById(R.id.add_class_instructor);
        RadioGroup dayCheckEdit = customLayout.findViewById(R.id.class_days);
        TimePicker timePickerEdit = customLayout.findViewById(R.id.class_time_picker);

        EditText className = view.findViewById(R.id.class_name);
        EditText classDate = view.findViewById(R.id.class_time);
        EditText classInstructor = view.findViewById(R.id.class_instructor);

        int index = classNames.indexOf(className.getText().toString());

        // set editing window to have same inputs as the selected view
        classNameEdit.setText(className.getText());
        classInstructorEdit.setText(classInstructor.getText());

        ArrayList<Integer> daysChecked = editedClass.getDaysChecked();
        int count = daysChecked.size();
        for (int i = 0; i < count; i++) {
            ((CheckBox) dayCheckEdit.getChildAt(daysChecked.get(i))).setChecked(true);
        }

        timePickerEdit.setHour(editedClass.getHour());
        timePickerEdit.setMinute(editedClass.getMinute());

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            String nameText, dateText, instructorText;

            // add empty check for date/time
            if (nonEmptyDialog(classNameEdit, classInstructorEdit)) {
                nameText = classNameEdit.getText().toString();
                dateText = getClassDateFromDialog(dayCheckEdit, timePickerEdit);
                instructorText = classInstructorEdit.getText().toString();

                boolean classExists = false;
                for (int i = 0;i < classNames.size(); i++) if (classNames.get(i).equals(nameText) && i != index) {
                    classExists = true;
                    break;
                }

                if (!classExists) {
                    className.setText(nameText);
                    classDate.setText(dateText);
                    classInstructor.setText(instructorText);
                    classNames.remove(index);
                    classNames.add(index, className.getText().toString());
                } else {
                    Toast.makeText(context, "Class already exists!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
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
    private String getClassDateFromDialog(RadioGroup radioGroup, TimePicker timePicker) {
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

    public static ArrayList<String> getClassNames() {
        return classNames;
    }
}