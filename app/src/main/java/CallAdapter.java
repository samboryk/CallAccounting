package com.example.callaccounting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    private List<Call> list = new ArrayList<>();
    private AppDatabase db;

    public void setList(List<Call> newList, AppDatabase database) {
        list.clear();
        list.addAll(newList);
        this.db = database;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Call call = list.get(position);

        Abonent abonent = db.abonentDao().getById(call.abonentId);
        City city = db.cityDao().getById(call.cityId);

        String abonentInfo = abonent != null ? abonent.phoneNumber : "Невідомий";
        String cityName = city != null ? city.name : "Невідоме місто";
        double dayTariff = city != null ? city.dayTariff : 0.0;
        double nightTariff = city != null ? city.nightTariff : 0.0;
        double tariff = "День".equals(call.timeOfDay) ? dayTariff : nightTariff;
        double cost = call.minutes * tariff;

        holder.dateText.setText(call.date);
        holder.abonentText.setText("Абонент: " + abonentInfo);
        holder.cityText.setText("Місто: " + cityName);
        holder.minutesText.setText(call.minutes + " хв");
        holder.timeOfDayText.setText(call.timeOfDay);
        holder.costText.setText(String.format("%.2f грн", cost));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, abonentText, cityText, minutesText, timeOfDayText, costText;

        ViewHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.tv_date);
            abonentText = itemView.findViewById(R.id.tv_abonent);
            cityText = itemView.findViewById(R.id.tv_city);
            minutesText = itemView.findViewById(R.id.tv_minutes);
            timeOfDayText = itemView.findViewById(R.id.tv_time_of_day);
            costText = itemView.findViewById(R.id.tv_cost);
        }
    }
}
