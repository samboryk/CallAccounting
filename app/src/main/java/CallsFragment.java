package com.example.callaccounting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class CallsFragment extends Fragment {
    private RecyclerView recyclerView;
    private CallAdapter adapter;
    private AppDatabase db;

    public CallsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls, container, false);

        db = AppDatabase.getInstance(requireContext());
        recyclerView = view.findViewById(R.id.recycler_calls);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new CallAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_call);
        fab.setOnClickListener(v -> showAddCallDialog());

        loadCalls();

        return view;
    }

    private void loadCalls() {
        List<Call> list = db.callDao().getAll();
        adapter.setList(list, db);
    }

    private void showAddCallDialog() {
        AddCallDialog dialog = new AddCallDialog();
        dialog.setOnCallAddedListener(this::loadCalls);
        dialog.show(getParentFragmentManager(), "add_call");
    }
}