package com.example.callaccounting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

public class AddCityDialog extends DialogFragment {
    private EditText etCityName, etDayTariff, etNightTariff;
    private Button btnSave, btnCancel;
    private OnCityAddedListener listener;

    public interface OnCityAddedListener {
        void onCityAdded();
    }

    public void setOnCityAddedListener(OnCityAddedListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_city, container, false);

        etCityName = view.findViewById(R.id.et_city_name);
        etDayTariff = view.findViewById(R.id.et_day_tariff);
        etNightTariff = view.findViewById(R.id.et_night_tariff);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(v -> saveCity());
        btnCancel.setOnClickListener(v -> dismiss());

        return view;
    }

    private void saveCity() {
        String name = etCityName.getText().toString().trim();
        String dayStr = etDayTariff.getText().toString().trim();
        String nightStr = etNightTariff.getText().toString().trim();

        if (name.isEmpty() || dayStr.isEmpty() || nightStr.isEmpty()) {
            return;
        }

        double dayTariff = Double.parseDouble(dayStr);
        double nightTariff = Double.parseDouble(nightStr);

        AppDatabase db = AppDatabase.getInstance(requireContext());
        City city = new City(name, dayTariff, nightTariff);
        db.cityDao().insert(city);

        if (listener != null) {
            listener.onCityAdded();
        }
        dismiss();
    }
}