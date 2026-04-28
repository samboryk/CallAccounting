import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public double dayTariff;
    public double nightTariff;

    public City() {}

    public City(String name, double dayTariff, double nightTariff) {
        this.name = name;
        this.dayTariff = dayTariff;
        this.nightTariff = nightTariff;
    }
}