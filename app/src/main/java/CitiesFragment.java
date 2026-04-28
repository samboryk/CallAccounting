import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class CitiesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private AppDatabase db;

    public CitiesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);

        db = AppDatabase.getInstance(requireContext());
        recyclerView = view.findViewById(R.id.recycler_cities);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new CityAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_city);
        fab.setOnClickListener(v -> showAddCityDialog());

        loadCities();

        return view;
    }

    private void loadCities() {
        List<City> list = db.cityDao().getAll();
        adapter.setList(list);
    }

    private void showAddCityDialog() {
        AddCityDialog dialog = new AddCityDialog();
        dialog.setOnCityAddedListener(this::loadCities);
        dialog.show(getParentFragmentManager(), "add_city");
    }
}