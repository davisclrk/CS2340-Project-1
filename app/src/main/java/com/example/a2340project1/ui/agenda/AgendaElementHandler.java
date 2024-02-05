package com.example.a2340project1.ui.agenda;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElementHandler;
import com.example.a2340project1.ui.classes.ClassElement;
import com.example.a2340project1.ui.classes.ClassElementHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Handles adding, removing, and editing dynamically added assignments and exams to the
 * agenda fragment. Implementations of these actions are the same as in
 * DynamicElementHandler.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class AgendaElementHandler extends DynamicElementHandler {

    private int displayDay, displayMonth, displayYear;
    private List<AgendaElement> AgendaElements = new ArrayList<>();

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields. Also adds a new class to the context.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)
     */
    public void agendaAddDialog(ViewGroup viewGroup, LayoutInflater inflater, Context context,
                                DatePickerDialog.OnDateSetListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.agenda_popup_dialog, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        Button addAssignment = customLayout.findViewById(R.id.add_assignment);
        addAssignment.setOnClickListener(v -> agendaAddAssignment(inflater, context, listener, viewGroup, dialog));

        Button addExam = customLayout.findViewById(R.id.add_exam);
        addExam.setOnClickListener(v -> agendaAddExam(inflater, context, listener, viewGroup, dialog));

        dialog.show();
    }

    /**
     * javadocs lol!!!
     *
     * @param inflater
     * @param context
     * @param listener
     * @param viewGroup
     */
    private void agendaAddAssignment(LayoutInflater inflater, Context context,
                                     DatePickerDialog.OnDateSetListener listener,
                                     ViewGroup viewGroup, AlertDialog agendaDialog) {
        AlertDialog.Builder assignmentBuilder = new AlertDialog.Builder(context);
        assignmentBuilder.setTitle("Add Assignment");

        View assignmentLayout = inflater.inflate(R.layout.assignment_popup_dialog, null);
        assignmentBuilder.setView(assignmentLayout);

        EditText assignmentDate = assignmentLayout.findViewById(R.id.add_assignment_date);
        assignmentDate.setEnabled(false);

        Spinner assignmentClass = assignmentLayout.findViewById(R.id.add_assignment_class_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                ClassElementHandler.getClassNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        assignmentClass.setAdapter(adapter);

        Button datePicker = assignmentLayout.findViewById(R.id.assignment_date_picker);
        datePicker.setOnClickListener(v1 -> showDatePickerDialog(listener, context, assignmentLayout));
//        datePicker.setText(displayMonth+"/"+displayDay+"/"+displayYear); // THIS DOES NOT WORK I DONT KNOW WHERE TO PUT THIS SO THAT IT SETS THE BUTTON TEXT AFTER YOU PICK THE DATE ON THE CALENDAR

        // add button
        assignmentBuilder.setPositiveButton("OK", (dialog, which) -> {
            EditText assignmentName = assignmentLayout.findViewById(R.id.add_assignment_name);
            TimePicker assignmentTime =  assignmentLayout.findViewById(R.id.assignment_time_picker);
            String assignmentDeadline = getAssignmentDeadlineFromDialog(assignmentTime);


            //agenda class is null string for now lol
            AssignmentElement newAssignment = new AssignmentElement(R.layout.assignment_grid,
                    assignmentName.getText().toString(), assignmentClass.getSelectedItem().toString(),
                    assignmentDeadline, displayMonth, displayDay, displayYear,
                    assignmentTime.getHour(), assignmentTime.getMinute(), assignmentClass.getSelectedItemPosition());

            int index = calculateViewPosition(newAssignment.getAgendaMonth(), newAssignment.getAgendaDay(), newAssignment.getAgendaYear(), newAssignment.getAgendaHour(), newAssignment.getAgendaMinute());
            // im using the same vars twice so should prob instantiate acutal vars above and then use them here

            AgendaElements.add(index, newAssignment);

            assignmentAddView(viewGroup, inflater, newAssignment, context, index);
            agendaDialog.dismiss();
        });
        assignmentBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog assignmentDialog = assignmentBuilder.create();
        assignmentDialog.show();
    }

    /**
     * A version of the superclass showEditDialog method, but with extra variables for the
     * different input fields.
     *
     * @see DynamicElementHandler#showEditDialog(String, ViewGroup, LayoutInflater, View, Context, int, int, int)
     */
    public void assignmentEditDialog(ViewGroup viewGroup, LayoutInflater inflater,
                                View view, AssignmentElement editedAssignment, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Class");

        // set the custom layout
        View customLayout = inflater.inflate(R.layout.assignment_popup_dialog, null);
        builder.setView(customLayout);

        EditText assignmentNameEdit = customLayout.findViewById(R.id.add_assignment_name);
        Spinner assignmentClassEdit = customLayout.findViewById(R.id.add_assignment_class_spinner);
        EditText assignmentDateEdit = customLayout.findViewById(R.id.add_assignment_date);
        TimePicker timePickerEdit = customLayout.findViewById(R.id.assignment_time_picker);

        assignmentDateEdit.setEnabled(false);

        EditText assignmentName = view.findViewById(R.id.assignment_title);
        EditText assignmentClass = view.findViewById(R.id.assignment_class);
        EditText assignmentDate = view.findViewById(R.id.assignment_deadline);

        //set editing window to have same inputs as the selected view
        assignmentNameEdit.setText(assignmentName.getText());
        assignmentClassEdit.setSelection(editedAssignment.getClassIndex());
        assignmentDateEdit.setText(assignmentDate.getText());

        timePickerEdit.setHour(editedAssignment.getAgendaHour());
        timePickerEdit.setMinute(editedAssignment.getAgendaMinute());

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            String nameText, dateText, classText;

            if (nonEmptyDialog(assignmentNameEdit)) {
                nameText = assignmentNameEdit.getText().toString();
                dateText = getAssignmentDeadlineFromDialog(timePickerEdit);
                classText = assignmentClassEdit.getSelectedItem().toString();

                assignmentName.setText(nameText);
                assignmentDate.setText(dateText);
                assignmentClass.setText(classText);
            }

        });
        builder.setNeutralButton("Delete", (dialog, which) -> viewGroup.removeView(view));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     *
     * @param timePicker
     * @return
     */
    private String getAssignmentDeadlineFromDialog(TimePicker timePicker) {
        String time, date, hour, minute, AMPM;
        hour = (timePicker.getHour() > 12) ?
                String.valueOf(timePicker.getHour() - 12) : String.valueOf(timePicker.getHour());
        minute = (timePicker.getMinute() < 10) ?
                "0" + timePicker.getMinute() : String.valueOf(timePicker.getMinute());
        AMPM = (timePicker.getHour() < 12) ? "AM" : "PM";
        time = hour + ":" + minute + " " + AMPM;

        date = displayMonth + "/" + displayDay + "/" + displayYear;

        return date + ", " + time;
    }

    /**
     * more javadocs haha
     *
     * @param viewGroup
     * @param inflater
     * @param addedAssignment
     * @param context
     */
    private void assignmentAddView(ViewGroup viewGroup, LayoutInflater inflater, AssignmentElement addedAssignment, Context context, int index) {
        View addedView = inflater.inflate(addedAssignment.getMainResource(), null, false);
        EditText assignmentName = addedView.findViewById(R.id.assignment_title);
        EditText assignmentClass = addedView.findViewById(R.id.assignment_class);
        EditText assignmentDeadline = addedView.findViewById(R.id.assignment_deadline); // i just realized all these var names are the same

        assignmentName.setEnabled(false);
        assignmentName.setText(addedAssignment.getAgendaName());
        assignmentClass.setEnabled(false);
        assignmentClass.setText(addedAssignment.getAgendaClass());
        assignmentDeadline.setEnabled(false);
        assignmentDeadline.setText(addedAssignment.getAgendaDate());

        viewGroup.addView(addedView, index);
    }


    /**
     * javadocs lmao!!!
     *
     * @param inflater
     * @param context
     * @param listener
     * @param viewGroup
     */
    private void agendaAddExam(LayoutInflater inflater, Context context, DatePickerDialog.OnDateSetListener listener, ViewGroup viewGroup, AlertDialog agendaDialog) {
        AlertDialog.Builder examBuilder = new AlertDialog.Builder(context);
        examBuilder.setTitle("Add Exam");

        View examLayout = inflater.inflate(R.layout.exam_popup_dialog, null);
        examBuilder.setView(examLayout);

        Button datePicker = examLayout.findViewById(R.id.exam_date_picker);
        datePicker.setOnClickListener(v1 -> showDatePickerDialog(listener, context, examLayout));

        // add button
        examBuilder.setPositiveButton("OK", (dialog, which) -> {
            EditText examName = examLayout.findViewById(R.id.add_exam_name);
            EditText examClass = examLayout.findViewById(R.id.add_exam_class);
            TimePicker examTime = examLayout.findViewById(R.id.exam_time_picker);
            EditText examLocation = examLayout.findViewById(R.id.add_exam_location);
            String examDate = getAssignmentDeadlineFromDialog(examTime);

            //placeholder 0 for index of class
            ExamElement newExam = new ExamElement(R.layout.exam_grid, examName.getText().toString(),
                    examClass.getText().toString(), examDate, displayMonth, displayDay, displayYear,
                    examTime.getHour(), examTime.getMinute(), 0,examLocation.getText().toString());
            AgendaElements.add(newExam);

            examAddView(viewGroup, inflater, newExam, context);
            agendaDialog.dismiss();
        });
        examBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog examDialog = examBuilder.create();
        examDialog.show();
    }

    /**
     * jdocs
     *
     * @param viewGroup
     * @param inflater
     * @param addedExam
     * @param context
     */
    private void examAddView(ViewGroup viewGroup, LayoutInflater inflater, ExamElement addedExam, Context context) {
        View addedView = inflater.inflate(addedExam.getMainResource(), null, false);
        EditText examName = addedView.findViewById(R.id.exam_title);
        EditText examClass = addedView.findViewById(R.id.exam_class);
        EditText examDate = addedView.findViewById(R.id.exam_datetime);
        EditText examLocation = addedView.findViewById(R.id.exam_location); // i just realized all these var names are the same

        examName.setEnabled(false);
        examName.setText(addedExam.getAgendaName());
        examClass.setEnabled(false);
        examClass.setText(addedExam.getAgendaClass());
        examDate.setEnabled(false);
        examDate.setText(addedExam.getAgendaDate());
        examLocation.setEnabled(false);
        examLocation.setText(addedExam.getLocation());

        // still need to add edit functionality

        viewGroup.addView(addedView);
    }

    /**
     * Displays dialog for calendar popup on "Pick Date" button click
     *
     * @param listener the onDateSet listener
     * @param context  the context of the destination fragment
     */
    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener, Context context,
                                      View dialog) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context, listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        //datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", (dialog1, which) -> {
            String date = displayMonth + "/" + displayDay + "/" + displayYear;
            ((EditText) dialog.findViewById(R.id.add_assignment_date)).setText(date);
        //});
        datePickerDialog.show();
    }

    /**
     * docs
     *
     * @param year
     * @param month
     * @param day
     */
    public void agendaSetDate(int year, int month, int day) {
        displayDay = day;
        displayMonth = month;
        displayYear = year;
    }

    /**
     * sort by class (not yet tested)
     *
     * @param viewGroup
     * @param inflater
     */
    public void agendaSortByClass(ViewGroup viewGroup, LayoutInflater inflater) { // made public for now but change back to private later
        ArrayList<AgendaElement> AgendaElementClassSort = new ArrayList<>(AgendaElements); // it shouldnt, but make sure that this doesnt refer to agendaElements by reference and accidentally change agendaElements or smoething
        // also consider moving this arraylist into the fields of the class because it would be lowkey a waste to rebuild it every time? then i wouldnt need to copy the agendaelements arraylist. but then i would need to also add to this arraylist every time (no need to worry about sorting tho)

        Collections.sort(AgendaElementClassSort, AgendaElement.dateSort);
        // HAS NOT BEEN TESTED YET. UNSURE IF FUNCTIONAL.

        viewGroup.removeAllViews();
        for (AgendaElement i:AgendaElementClassSort) {
            View newView = inflater.inflate(i.getMainResource(), null, false);
            Log.i("VIEWHERE", i.getAgendaClass().toString());
            viewGroup.addView(newView); // uhhh it does NOT repopulate the fields rn gotta figure that out lol
        }
    }

    /**
     * sort by date (not yet tested)
     *
     * @param viewGroup
     * @param inflater
     */
    private void agendaSortByDate(ViewGroup viewGroup, LayoutInflater inflater) {
        viewGroup.removeAllViews();
        for (AgendaElement i:AgendaElements) {
            View newView = inflater.inflate(i.getMainResource(), null, false);
            viewGroup.addView(newView); // uhhh it does NOT repopulate the fields rn gotta figure that out lol
            // it seems to be sorting the assignments by class fine tho.
        }
        // HAS NOT BEEN TESTED YET. UNSURE IF FUNCTIONAL.
    }

    /**
     *
     * finds the view position in the linear layout based on date and time. rewrite this javadocs later ofc
     *
     * @param month
     * @param day
     * @param year
     * @param hour
     * @param minute
     * @return
     */
    private int calculateViewPosition(int month, int day, int year, int hour, int minute) {
        int index = 0;

        while (index < AgendaElements.size() && year > AgendaElements.get(index).getAgendaYear()) index++;
        while (index < AgendaElements.size() && year == AgendaElements.get(index).getAgendaYear() && month > AgendaElements.get(index).getAgendaMonth()) index++;
        while (index < AgendaElements.size() && year == AgendaElements.get(index).getAgendaYear() && month == AgendaElements.get(index).getAgendaMonth() &&
                day > AgendaElements.get(index).getAgendaDay()) index++;
        while (index < AgendaElements.size() && year == AgendaElements.get(index).getAgendaYear() && month == AgendaElements.get(index).getAgendaMonth() &&
                day == AgendaElements.get(index).getAgendaDay() && hour > AgendaElements.get(index).getAgendaHour()) index++;
        while (index < AgendaElements.size() && year == AgendaElements.get(index).getAgendaYear() && month == AgendaElements.get(index).getAgendaMonth() &&
                day == AgendaElements.get(index).getAgendaDay() && hour == AgendaElements.get(index).getAgendaHour() && minute > AgendaElements.get(index).getAgendaMinute()) index++;

        return index;
    }
}
