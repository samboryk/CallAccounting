import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

public class AddAbonentDialog extends DialogFragment {
    private EditText etPhone, etInn, etAddress;
    private Button btnSave, btnCancel;
    private OnAbonentAddedListener listener;

    public interface OnAbonentAddedListener {
        void onAbonentAdded();
    }

    public void setOnAbonentAddedListener(OnAbonentAddedListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_abonent, container, false);

        etPhone = view.findViewById(R.id.et_phone);
        etInn = view.findViewById(R.id.et_inn);
        etAddress = view.findViewById(R.id.et_address);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(v -> saveAbonent());
        btnCancel.setOnClickListener(v -> dismiss());

        return view;
    }

    private void saveAbonent() {
        String phone = etPhone.getText().toString().trim();
        String inn = etInn.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (phone.isEmpty() || inn.isEmpty() || address.isEmpty()) {
            return;
        }

        AppDatabase db = AppDatabase.getInstance(requireContext());
        Abonent abonent = new Abonent(phone, inn, address);
        db.abonentDao().insert(abonent);

        if (listener != null) {
            listener.onAbonentAdded();
        }
        dismiss();
    }
}