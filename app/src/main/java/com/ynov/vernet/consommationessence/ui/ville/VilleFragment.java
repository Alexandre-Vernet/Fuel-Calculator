package com.ynov.vernet.consommationessence.ui.ville;

import android.annotation.SuppressLint;
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

import com.ynov.vernet.consommationessence.Car;
import com.ynov.vernet.consommationessence.R;

public class VilleFragment extends Fragment {

    EditText editTextDistance;
    double price, fuelConsumption;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ville, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDistance = getView().findViewById(R.id.editTextDistance);

        view.findViewById(R.id.btnValider).setOnClickListener(v -> {
            if (!editTextDistance.getText().toString().isEmpty()) {
                double distance = Double.parseDouble(editTextDistance.getText().toString());

                Car car = new Car();
                price = car.calculatePrice(distance);
                fuelConsumption = car.calculateFuelConsumption(distance);

                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle(R.string.calculateur)
                        .setMessage(getString(R.string.votre_trajet_coutera, String.valueOf(price), String.valueOf(fuelConsumption)))
                        .setPositiveButton("Ok", (dialogInterface, i) -> {
                        })
                        .show();
            } else {
                editTextDistance.setError(getString(R.string.erreur));
                new Handler().postDelayed(() -> editTextDistance.setError(null), 2000);
            }
        });
    }
}
