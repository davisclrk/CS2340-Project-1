package com.example.a2340project1.ui.toDo;

import android.content.Context;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentToDoBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ToDoFragment extends Fragment {

    private FragmentToDoBinding binding;
    private LinearLayout layoutList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel toDoViewModel =
                new ViewModelProvider(this).get(ToDoViewModel.class);

        binding = FragmentToDoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //destination for added elements
        layoutList = root.findViewById(R.id.todo_linearlayout);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton toDoAddButton = view.findViewById(R.id.add_todolists_button);
        toDoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDoView(layoutList);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void removeView(ViewGroup viewGroup, View view) {
        viewGroup.removeView(view);
    }

    private void addToDoView(ViewGroup viewGroup) {
        View toDoView = getLayoutInflater().inflate(R.layout.todo_row, null, false);

        //EditText editText = (EditText) toDoView.findViewById(R.id.todo_edittext);
        ImageButton imageClose = (ImageButton) toDoView.findViewById(R.id.todo_image_remove);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(viewGroup, toDoView);
            }
        });

        viewGroup.addView(toDoView);
    }








}