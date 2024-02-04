package com.example.a2340project1.ui.agenda;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentAgendaBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AgendaFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentAgendaBinding binding;

    private int month, day, year;

    private LinearLayout layoutList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AgendaViewModel agendaViewModel =
                new ViewModelProvider(this).get(AgendaViewModel.class);

        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutList = root.findViewById(R.id.agenda_layout);

        return root;
    }

    // NOTE: there should be 2 filters at the top, one for showing exam/assignment/both and one for sorting by date/class

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton agendaAddButton = view.findViewById(R.id.add_agenda_button);
        Context context = this.getContext();
        LayoutInflater inflater = getLayoutInflater();
        agendaAddButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               // set the custom layout
               View customLayout = inflater.inflate(R.layout.agenda_popup_dialog, null);
               builder.setView(customLayout);
               AlertDialog dialog = builder.create();

               Button addAssignment = customLayout.findViewById(R.id.add_assignment);
               addAssignment.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       AlertDialog.Builder assignmentBuilder = new AlertDialog.Builder(context);
                       assignmentBuilder.setTitle("Add Assignment");
                       View assignmentLayout = inflater.inflate(R.layout.assignment_grid, null);
                       assignmentBuilder.setView(assignmentLayout);
                       AlertDialog assignmentDialog = assignmentBuilder.create();
                       assignmentDialog.show();


                       Button datePicker = assignmentLayout.findViewById(R.id.assignment_date_picker);

                       datePicker.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               showDatePickerDialog();
                           }
                       });

//                       dialog.dismiss(); should dismiss once the assignment is actually created in case the user wants to return
                   }
               });

               Button addExam = customLayout.findViewById(R.id.add_exam);
               addExam.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       // actual functionality not implemented
                       dialog.dismiss();
                   }
               });

               dialog.show();
           }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.requireContext(),  // or maybe change to this.getContext() if its not working
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
//        this.month = month;
//        this.day = dayOfMonth;
//        this.year = year;
    }
}