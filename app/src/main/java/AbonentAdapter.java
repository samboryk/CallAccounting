package com.example.callaccounting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AbonentAdapter extends RecyclerView.Adapter<AbonentAdapter.ViewHolder> {
    private List<Abonent> list = new ArrayList<>();

    public void setList(List<Abonent> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_abonent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Abonent abonent = list.get(position);
        holder.phoneText.setText(abonent.phoneNumber);
        holder.innText.setText(abonent.inn);
        holder.addressText.setText(abonent.address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView phoneText, innText, addressText;

        ViewHolder(View itemView) {
            super(itemView);
            phoneText = itemView.findViewById(R.id.tv_phone);
            innText = itemView.findViewById(R.id.tv_inn);
            addressText = itemView.findViewById(R.id.tv_address);
        }
    }
}
