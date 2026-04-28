import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.DialogFragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCallDialog extends DialogFragment {
    private Spinner spinnerAbonent, spinnerCity, spinnerTimeOfDay;
    private EditText etMinutes;
    private Button btnSave, btnCancel;
    private OnCallAddedListener listener;

    public interface OnCallAddedListener {
        void onCallAdded();
    }

    public void setOnCallAddedListener(OnCallAddedListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_call, container, false);

        spinnerAbonent = view.findViewById(R.id.spinner_abonent);
        spinnerCity = view.findViewById(R.id.spinner_city);
        spinnerTimeOfDay = view.findViewById(R.id.spinner_time_of_day);
        etMinutes = view.findViewById(R.id.et_minutes);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);

        loadSpinners();

        btnSave.setOnClickListener(v -> saveCall());
        btnCancel.setOnClickListener(v -> dismiss());

        return view;
    }

    private void loadSpinners() {
        AppDatabase db = AppDatabase.getInstance(requireContext());

        List<Abonent> abonents = db.abonentDao().getAll();
        ArrayAdapter<Abonent> abonentAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, abonents);
        abonentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAbonent.setAdapter(abonentAdapter);

        List<City> cities = db.cityDao().getAll();
        ArrayAdapter<City> cityAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        String[] timeOptions = {"День", "Ніч"};
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, timeOptions);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeOfDay.setAdapter(timeAdapter);
    }

    private void saveCall() {
        if (spinnerAbonent.getSelectedItem() == null || spinnerCity.getSelectedItem() == null) {
            return;
        }

        Abonent selectedAbonent = (Abonent) spinnerAbonent.getSelectedItem();
        City selectedCity = (City) spinnerCity.getSelectedItem();
        String timeOfDay = (String) spinnerTimeOfDay.getSelectedItem();

        String minutesStr = etMinutes.getText().toString().trim();
        if (minutesStr.isEmpty()) {
            return;
        }
        int minutes = Integer.parseInt(minutesStr);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

        AppDatabase db = AppDatabase.getInstance(requireContext());
        Call call = new Call(selectedAbonent.id, selectedCity.id, currentDate, minutes, timeOfDay);
        db.callDao().insert(call);

        if (listener != null) {
            listener.onCallAdded();
        }
        dismiss();
    }
}