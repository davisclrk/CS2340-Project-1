package com.example.a2340project1.ui.agenda;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a2340project1.R;
import com.example.a2340project1.ui.DynamicElementHandler;

import java.util.Calendar;

/**
 * Handles adding, removing, and editing dynamically added assignments and exams to the
 * agenda fragment. Implementations of these actions are the same as in
 * DynamicElementHandler.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class AgendaElementHandler extends DynamicElementHandler {
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
        addAssignment.setOnClickListener(v -> {
            AlertDialog.Builder assignmentBuilder = new AlertDialog.Builder(context);
            assignmentBuilder.setTitle("Add Assignment");
            View assignmentLayout = inflater.inflate(R.layout.assignment_grid, null);
            assignmentBuilder.setView(assignmentLayout);
            AlertDialog assignmentDialog = assignmentBuilder.create();
            assignmentDialog.show();


            Button datePicker = assignmentLayout.findViewById(R.id.assignment_date_picker);

            datePicker.setOnClickListener(v1 -> showDatePickerDialog(listener, context));

            //actually adds the view to the context
            //viewGroup.addView(assignmentLayout);

//                       dialog.dismiss(); should dismiss once the assignment is actually created in case the user wants to return
        });
        Button addExam = customLayout.findViewById(R.id.add_exam);
        addExam.setOnClickListener(v -> {
            // actual functionality not implemented
            dialog.dismiss();
        });
        dialog.show();
    }

    /**
     * Displays dialog for calendar popup on "Pick Date" button click
     *
     * @param listener the onDateSet listener
     * @param context the context of the destination fragment
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
}
