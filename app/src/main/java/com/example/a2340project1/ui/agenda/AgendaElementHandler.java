package com.example.a2340project1.ui.agenda;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElementHandler;

import java.util.ArrayList;
import java.util.Calendar;
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

        Button datePicker = assignmentLayout.findViewById(R.id.assignment_date_picker);
        datePicker.setOnClickListener(v1 -> showDatePickerDialog(listener, context));
//        datePicker.setText(displayMonth+"/"+displayDay+"/"+displayYear); // THIS DOES NOT WORK I DONT KNOW WHERE TO PUT THIS SO THAT IT SETS THE BUTTON TEXT AFTER YOU PICK THE DATE ON THE CALENDAR

        // add button
        assignmentBuilder.setPositiveButton("OK", (dialog, which) -> {
            EditText assignmentName = assignmentLayout.findViewById(R.id.add_assignment_name);
            EditText assignmentClass = assignmentLayout.findViewById(R.id.add_assignment_class);
            TimePicker assignmentTime =  assignmentLayout.findViewById(R.id.assignment_time_picker);
            String assignmentDeadline = getAssignmentDeadlineFromDialog(assignmentTime);

            AssignmentElement newAssignment = new AssignmentElement(R.layout.assignment_grid,
                    assignmentName.getText().toString(), assignmentClass.getText().toString(), assignmentDeadline, displayMonth, displayDay, displayYear,
                    assignmentTime.getHour(), assignmentTime.getMinute()
                    );
            AgendaElements.add(newAssignment); // remember to use the hashmap implementation too

            assignmentAddView(viewGroup, inflater, newAssignment, context);
            agendaDialog.dismiss();
        });
        assignmentBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog assignmentDialog = assignmentBuilder.create();
        assignmentDialog.show();
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
    private void assignmentAddView(ViewGroup viewGroup, LayoutInflater inflater, AssignmentElement addedAssignment, Context context) {
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

        // still need to add editing functionality

        viewGroup.addView(addedView);
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
        datePicker.setOnClickListener(v1 -> showDatePickerDialog(listener, context));

        // add button
        examBuilder.setPositiveButton("OK", (dialog, which) -> {
            EditText examName = examLayout.findViewById(R.id.add_exam_name);
            EditText examClass = examLayout.findViewById(R.id.add_exam_class);
            TimePicker examTime = examLayout.findViewById(R.id.exam_time_picker);
            EditText examLocation = examLayout.findViewById(R.id.add_exam_location);
            String examDate = getAssignmentDeadlineFromDialog(examTime);

            ExamElement newExam = new ExamElement(R.layout.exam_grid, examName.getText().toString(), examClass.getText().toString(), examDate, displayMonth, displayDay, displayYear, examTime.getHour(), examTime.getMinute(), examLocation.getText().toString());
            AgendaElements.add(newExam);

            examAddView(viewGroup, inflater, newExam, context);
            agendaDialog.dismiss();
        });
        examBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog examDialog = examBuilder.create();
        examDialog.show();
    }

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
    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener, Context context) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context, listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void agendaSetDate(int year, int month, int day) {
        displayDay = day;
        displayMonth = month;
        displayYear = year;
    }

}
