import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CallDao {
    @Insert
    void insert(Call call);

    @Update
    void update(Call call);

    @Delete
    void delete(Call call);

    @Query("SELECT * FROM calls ORDER BY date DESC")
    List<Call> getAll();

    @Query("SELECT * FROM calls WHERE abonentId = :abonentId ORDER BY date DESC")
    List<Call> getByAbonent(int abonentId);
}