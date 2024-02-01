package com.example.a2340project1.ui.toDo;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentToDoBinding;
import com.example.a2340project1.ui.DynamicElementHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {
    private FragmentToDoBinding binding;
    private LinearLayout layoutList;
    private static final DynamicElementHandler dynamicElementHandler = new DynamicElementHandler();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel toDoViewModel =
                new ViewModelProvider(this).get(ToDoViewModel.class);

        binding = FragmentToDoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //destination for added elements
        layoutList = root.findViewById(R.id.todo_linearlayout);

        //might switch to viewmodel
        int temp = dynamicElementHandler.getViewCount();
        dynamicElementHandler.setViewCount(0);
        for (int i = 0; i < temp; i++) {
            dynamicElementHandler.addView(layoutList, getLayoutInflater(), R.layout.todo_row, R.id.todo_image_remove);
        }

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton toDoAddButton = view.findViewById(R.id.add_todolists_button);
        toDoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicElementHandler.addView(layoutList, getLayoutInflater(), R.layout.todo_row, R.id.todo_image_remove);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}