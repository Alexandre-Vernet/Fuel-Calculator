package com.ynov.vernet.consommationessence;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            String averageConsumption = "averageConsumption";
            Preference preferenceAverageConsumption = findPreference(averageConsumption);
            preferenceAverageConsumption.setOnPreferenceChangeListener((preference, newValue) -> {
                if (checkIfPreferenceIsValidNumber((String) newValue)) {
                    storePreference(averageConsumption, (String) newValue);
                    return true;
                }
                return false;
            });


            String fuelPrice = "fuelPrice";
            Preference preferencefuelPrice = findPreference(fuelPrice);
            preferencefuelPrice.setOnPreferenceChangeListener((preference, newValue) -> {
                if (checkIfPreferenceIsValidNumber((String) newValue)) {
                    storePreference(fuelPrice, (String) newValue);
                    return true;
                }
                return false;
            });
        }

        private boolean checkIfPreferenceIsValidNumber(String value) {
            try {
                Double.parseDouble(value);
                return true;
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), getString(R.string.enter_valid_number), Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        private void storePreference(String key, String value) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
            editor.putString(key, value);
            editor.apply();
        }
    }
}
