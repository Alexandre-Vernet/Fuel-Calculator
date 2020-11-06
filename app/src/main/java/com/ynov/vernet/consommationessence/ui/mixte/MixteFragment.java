package com.ynov.vernet.consommationessence.ui.mixte;

import android.os.Bundle;
import android.os.Handler;
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

public class MixteFragment extends Fragment {

    EditText editTextDistance;

    private MixteViewModel mixteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mixteViewModel = new ViewModelProvider(this).get(MixteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mixte, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDistance = getView().findViewById(R.id.editTextDistance);

        // Au clic du bouton valider
        view.findViewById(R.id.btnValider).setOnClickListener(v -> {
            // Si la zone est vide
            if (editTextDistance.getText().toString().isEmpty()) {

                // Afficher un message d'erreur
                editTextDistance.setError(getString(R.string.erreur));
                new Handler().postDelayed(() -> {
                    editTextDistance.setError(null);
                }, 2000);

                // Sinon
            } else {
                // Récupérer la valeur de la zone de texte en réel
                double valeur = Double.parseDouble(editTextDistance.getText().toString());

                // Calcul du prix
                double prix = valeur * 1.5 / 17.24;
                prix = Math.floor(prix * 100) / 100;                    /*2 chiffres après la virgule*/

                // Calcul de la consommation
                double consommation = valeur * 1 / 17.24;
                consommation = Math.floor(consommation * 100) / 100;    /*2 chiffres après la virgule*/

                // Afficher le résultat
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle(R.string.calculateur)
                        .setMessage(getString(R.string.votre_trajet_coûtera) + prix + " €\n" + getString(R.string.Vous_consommerez) + consommation + " L")
                        .setPositiveButton("Ok", (dialogInterface, i) -> {
                        })
                        .show();
            }
        });
    }
}