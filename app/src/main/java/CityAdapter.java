package com.example.callaccounting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<City> list = new ArrayList<>();

    public void setList(List<City> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = list.get(position);
        holder.nameText.setText(city.name);
        holder.dayTariffText.setText("День: " + city.dayTariff);
        holder.nightTariffText.setText("Ніч: " + city.nightTariff);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView dayTariffText;
        TextView nightTariffText;

        ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.tv_city_name);
            dayTariffText = itemView.findViewById(R.id.tv_day_tariff);
            nightTariffText = itemView.findViewById(R.id.tv_night_tariff);
        }
    }
}