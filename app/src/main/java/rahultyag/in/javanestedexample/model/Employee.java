
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
  /*  @PrimaryKey(autoGenerate = true)
    public int id;*/
    
    @SerializedName("area")
    @Expose
    private String area;
    
    @PrimaryKey
    @NonNull
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("territory")
    @Expose
    private String territory;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "area='" + area + '\'' +
                ", name='" + name + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
