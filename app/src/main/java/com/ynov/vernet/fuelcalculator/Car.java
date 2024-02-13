package com.ynov.vernet.fuelcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class Car extends AppCompatActivity {

    private final double fuelPrice;
    private final double averageConsumption;

    public Car(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        fuelPrice = Double.parseDouble(prefs.getString("fuelPrice", "1.890"));
        averageConsumption = Double.parseDouble(prefs.getString("averageConsumption", "5"));
    }

    public double calculatePrice(double distance) {
        double fuelConsumption = calculateFuelConsumption(distance);
        double price = fuelConsumption * fuelPrice;
        price = Math.floor(price * 100) / 100;
        return price;
    }

    public double calculateFuelConsumption(double distance) {
        double fuelConsumption = (distance * averageConsumption) / 100;
        fuelConsumption = Math.floor(fuelConsumption * 100) / 100;
        return fuelConsumption;
    }
}
