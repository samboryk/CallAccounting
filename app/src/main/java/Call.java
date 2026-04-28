package com.example.callaccounting;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "calls",
        foreignKeys = {
                @ForeignKey(entity = Abonent.class,
                        parentColumns = "id",
                        childColumns = "abonentId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = City.class,
                        parentColumns = "id",
                        childColumns = "cityId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Call {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int abonentId;
    public int cityId;
    public String date;
    public int minutes;
    public String timeOfDay;

    public Call() {}

    public Call(int abonentId, int cityId, String date, int minutes, String timeOfDay) {
        this.abonentId = abonentId;
        this.cityId = cityId;
        this.date = date;
        this.minutes = minutes;
        this.timeOfDay = timeOfDay;
    }
}