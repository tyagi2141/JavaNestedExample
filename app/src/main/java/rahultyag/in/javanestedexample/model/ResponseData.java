
package rahultyag.in.javanestedexample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ResponseData {
  /*  @PrimaryKey(autoGenerate = true)
    public int id;*/
  @PrimaryKey
  @NonNull
    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("zone")
    @Expose
    private List<Zone> zone = null;
    @SerializedName("region")
    @Expose
    private List<Region> region = null;
    @SerializedName("area")
    @Expose
    private List<Area> area = null;
    @SerializedName("employee")
    @Expose
    private List<Employee> employee = null;

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public List<Zone> getZone() {
        return zone;
    }

    public void setZone(List<Zone> zone) {
        this.zone = zone;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }

    public List<Area> getArea() {
        return area;
    }

    public void setArea(List<Area> area) {
        this.area = area;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    
    @Override
    public String toString() {
        return "ResponseData{" +
                "country=" + country +
                ", zone=" + zone +
                ", region=" + region +
                ", area=" + area +
                ", employee=" + employee +
                '}';
    }
}
