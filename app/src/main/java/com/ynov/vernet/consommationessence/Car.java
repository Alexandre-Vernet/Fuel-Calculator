package com.ynov.vernet.consommationessence;

public class Car {

    double averageConsumption = 7.2;
    double fuelPrice = 1.890;

    public double calculatePrice(double distance) {
        double price = (distance / 100) * fuelPrice;
        price = Math.floor(price * 100) / 100;
        return price;
    }

    public double calculateFuelConsumption(double distance) {
        double fuelConsumption = (distance / 100) * averageConsumption;
        fuelConsumption = Math.floor(fuelConsumption * 100) / 100;
        return fuelConsumption;
    }
}
