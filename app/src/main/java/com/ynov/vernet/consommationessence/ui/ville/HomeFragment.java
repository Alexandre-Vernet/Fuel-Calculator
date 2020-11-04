package com.ynov.vernet.consommationessence.ui.ville;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ynov.vernet.consommationessence.R;

public class HomeFragment extends Fragment {

    EditText editTextDistance;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ville, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDistance = getView().findViewById(R.id.editTextDistance);

        // Au clic du bouton valider
        view.findViewById(R.id.btnValider).setOnClickListener(v -> {

            // Récupérer la valeur de la zone de texte en réel
            String value = editTextDistance.getText().toString();
            double finalValue = Integer.parseInt(value);

            // Calcul du prix
            double prix = finalValue * 1.5 / 12.82;
            prix = Math.floor(prix * 100) / 100;                    /*2 chiffres après la virgule*/

            // Calcul de la consommation
            double consommation = finalValue * 1 / 12.82;
            consommation = Math.floor(consommation * 100) / 100;    /*2 chiffres après la virgule*/

            // Afficher le résultat
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Calculateur")
                    .setMessage("Votre trajet coûtera " + prix + " € \n Vous consommerez " + consommation + " L")
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                    })
                    .show();
        });

    }
}