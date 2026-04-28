import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "abonents")
public class Abonent {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String phoneNumber;
    public String inn;
    public String address;

    public Abonent() {}

    public Abonent(String phoneNumber, String inn, String address) {
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.address = address;
    }
}