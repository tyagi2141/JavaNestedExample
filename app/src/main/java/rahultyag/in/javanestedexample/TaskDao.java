package rahultyag.in.javanestedexample;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import rahultyag.in.javanestedexample.model.Area;
import rahultyag.in.javanestedexample.model.Country;
import rahultyag.in.javanestedexample.model.Employee;
import rahultyag.in.javanestedexample.model.Region;
import rahultyag.in.javanestedexample.model.Zone;

@Dao
public interface TaskDao {
	
	//Area
	@Query("SELECT * FROM Area")
	List<Area> getAllArea();

	
	
	@Query("SELECT * FROM Area WHERE area LIKE :title")
	List<Area> getAllArea(String title);
	
	
	@Insert
	void insertAllArea(Area task);
	
	@Query("DELETE FROM Area")
	void deleteArea();
	
	@Update
	void updateArea( Area area);
	
	//Country
	
	@Query("SELECT * FROM Country")
	List<Country> getAllCountry();
	
	@Query("SELECT * FROM Country WHERE country LIKE :country")
	List<Country> findCountryById(String country);
	
	@Insert
	void insertAllCountry( Country country);
	
	@Query("DELETE FROM Country")
	void deleteCountry();
	
	@Update
	void updateCountry( Area area);
	
	
	//Employee
	
	@Query("SELECT * FROM Employee")
	List<Employee> getAllEmployee();
	
	@Query("SELECT * FROM Employee WHERE area LIKE :area")
	List<Employee> findEmployeeById( String area);
	
	@Insert
	void insertAllEmployee( Employee employee);
	
	@Query("DELETE FROM Employee")
	void deleteEmployee();
	
	@Update
	void updateEmployee( Employee employee);
	
	
	//Region
	
	//Employee
	
	@Query("SELECT * FROM Region")
	List<Region> getAllRegion();
	
	@Query("SELECT * FROM Region WHERE region LIKE :region")
	List<Region> findRegionById( String region);
	
	@Insert
	void insertAllRegion(Region region);
	
	@Query("DELETE FROM Region")
	void deleteRegion();
	
	@Update
	void updateRegion( Region region);
	
	//Zone
	
	@Query("SELECT * FROM Zone")
	List<Zone> getAllZone();
	
	@Query("SELECT * FROM Zone WHERE zone LIKE :zone")
	List<Zone> findZoneId( String zone);
	
	@Insert
	void insertAllZone( Zone zone);
	
	@Query("DELETE FROM Zone")
	void deleteZone();
	
	@Update
	void updateZone( Zone zone);
}
