package rahultyag.in.javanestedexample;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import rahultyag.in.javanestedexample.model.Area;
import rahultyag.in.javanestedexample.model.Country;
import rahultyag.in.javanestedexample.model.Employee;
import rahultyag.in.javanestedexample.model.Region;
import rahultyag.in.javanestedexample.model.Zone;

@Database(entities = {Area.class, Country.class, Employee.class, Region.class, Zone.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
	public abstract TaskDao taskDao();
}