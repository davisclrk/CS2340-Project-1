package com.example.a2340project1.ui.toDo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentToDoBinding;

public class ToDoFragment extends Fragment {
    private FragmentToDoBinding binding;
    private LinearLayout layoutList;
    private static final ToDoElementHandler ELEMENT_HANDLER = new ToDoElementHandler();
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
        Button toDoAddButton = view.findViewById(R.id.add_todolists_button);
        toDoAddButton.setOnClickListener(view1 -> addView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addView() {
        EditText inputTask = binding.getRoot().findViewById(R.id.add_task_text);

        if (inputTask.getText().toString().equals("")) {
            return;
        }

        ToDoElement task = new ToDoElement(R.layout.todo_row, R.id.delete_task, inputTask.getText().toString());
        ELEMENT_HANDLER.addView(layoutList, getLayoutInflater(), task, this.getContext());
        inputTask.getText().clear();
    }
}