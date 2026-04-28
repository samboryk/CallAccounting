import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class AbonentsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AbonentAdapter adapter;
    private AppDatabase db;

    public AbonentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abonents, container, false);

        db = AppDatabase.getInstance(requireContext());
        recyclerView = view.findViewById(R.id.recycler_abonents);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new AbonentAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_abonent);
        fab.setOnClickListener(v -> showAddAbonentDialog());

        loadAbonents();

        return view;
    }

    private void loadAbonents() {
        List<Abonent> list = db.abonentDao().getAll();
        adapter.setList(list);
    }

    private void showAddAbonentDialog() {
        AddAbonentDialog dialog = new AddAbonentDialog();
        dialog.setOnAbonentAddedListener(this::loadAbonents);
        dialog.show(getParentFragmentManager(), "add_abonent");
    }
}