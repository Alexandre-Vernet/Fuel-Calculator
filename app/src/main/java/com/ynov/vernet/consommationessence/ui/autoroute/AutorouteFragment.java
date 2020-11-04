package com.ynov.vernet.consommationessence.ui.autoroute;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ynov.vernet.consommationessence.R;

public class AutorouteFragment extends Fragment {

    private AutorouteViewModel autorouteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        autorouteViewModel = new ViewModelProvider(this).get(AutorouteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_autoroute, container, false);
        return root;
    }
}