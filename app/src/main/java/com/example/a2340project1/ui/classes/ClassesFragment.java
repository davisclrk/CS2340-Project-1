package com.example.a2340project1.ui.classes;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.a2340project1.ui.DynamicElementHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;

    private LinearLayout layoutList;
    private static final DynamicElementHandler dynamicElementHandler = new DynamicElementHandler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClassesViewModel classesViewModel =
                new ViewModelProvider(this).get(ClassesViewModel.class);

        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutList = root.findViewById(R.id.classes_layout);

        int temp = dynamicElementHandler.getViewCount();
        dynamicElementHandler.setViewCount(0);
        for (int i = 0; i < temp; i++) {
            dynamicElementHandler.addView(layoutList, getLayoutInflater(), R.layout.class_grid, R.id.delete_class);
        }

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton classAddButton = view.findViewById(R.id.add_classes_button);
        classAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicElementHandler.addView(layoutList, getLayoutInflater(), R.layout.class_grid, R.id.delete_class);
            }
        });
    }

    /*
    private void addClass(ViewGroup viewGroup) {
        View classView = getLayoutInflater().inflate(R.layout.class_grid, null, false);

        ImageButton editClass = (ImageButton) classView.findViewById(R.id.edit_class);
        // add functionality for edit class later. make sure to make the text uneditable when the edit button has not been pressed
        // how would i know when to close the edit buton though? how would we "stop editing" the class details?

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
    */



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}