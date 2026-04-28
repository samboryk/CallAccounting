import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AbonentDao {
    @Insert
    void insert(Abonent abonent);

    @Update
    void update(Abonent abonent);

    @Delete
    void delete(Abonent abonent);

    @Query("SELECT * FROM abonents ORDER BY phoneNumber")
    List<Abonent> getAll();

    @Query("SELECT * FROM abonents WHERE id = :id")
    Abonent getById(int id);
}