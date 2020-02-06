
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Area {
    
   /* @PrimaryKey(autoGenerate = true)
   public int id;*/
    
    @PrimaryKey
    @NonNull
    @SerializedName("area")
    @Expose
    private String area="";
    @SerializedName("territory")
    @Expose
    private String territory="";

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Override
    public String toString() {
        return "Area{" +
                "area='" + area + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
