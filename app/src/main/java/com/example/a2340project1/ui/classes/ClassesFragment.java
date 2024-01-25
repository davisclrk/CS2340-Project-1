package com.example.a2340project1.ui.classes;

import android.media.Image;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentClassesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;

    private LinearLayout layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClassesViewModel classesViewModel =
                new ViewModelProvider(this).get(ClassesViewModel.class);

        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layout = root.findViewById(R.id.classes_layout);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton classAddButton = view.findViewById(R.id.add_classes_button);
        classAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass(layout);
            }
        });
    }

    private void addClass(ViewGroup viewGroup) {
        View classView = getLayoutInflater().inflate(R.layout.class_grid, null, false); // what is this doin? idk

        ImageButton editClass = (ImageButton) classView.findViewById(R.id.edit_class);
        EditText className = (EditText) classView.findViewById(R.id.class_name);
        EditText classInstructor = (EditText) classView.findViewById(R.id.class_instructor);
        EditText classTime = (EditText) classView.findViewById(R.id.class_time);
        // functionality for class edit button
        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditText(className);
                toggleEditText(classInstructor);
                toggleEditText(classTime);
            }
        });

        ImageButton deleteClass = (ImageButton) classView.findViewById(R.id.delete_class);
        // functionality for class delete button
        deleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGroup.removeView(classView);
            }
        });
        viewGroup.addView(classView);
    }

    private void toggleEditText(EditText text) {
        if (text.getKeyListener() != null) {
            text.setTag(text.getKeyListener());
            text.setKeyListener(null);
        } else {
            text.setKeyListener((KeyListener) text.getTag());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}