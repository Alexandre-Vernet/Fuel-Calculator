package com.ynov.vernet.consommationessence.ui.mixte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ynov.vernet.consommationessence.R;

public class MixteFragment extends Fragment {

    private MixteViewModel mixteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mixteViewModel = new ViewModelProvider(this).get(MixteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mixte, container, false);
        return root;
    }
}