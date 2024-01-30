package com.example.a2340project1.ui.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a2340project1.R;
import com.example.a2340project1.databinding.FragmentAgendaBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgendaFragment extends Fragment {

    private FragmentAgendaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AgendaViewModel agendaViewModel =
                new ViewModelProvider(this).get(AgendaViewModel.class);

        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    // NOTE: there should be 2 filters at the top, one for showing exam/assignment/both and one for sorting by date/class

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_classes_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // create a popup where u choose whether to create an assignment or exam
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}