
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Zone {
   /* @PrimaryKey(autoGenerate = true)
    public int id;*/
   @PrimaryKey
   @NonNull
    @SerializedName("zone")
    @Expose
    private String zone="";
    @SerializedName("territory")
    @Expose
    private String territory="";

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Override
    public String toString() {
        return "Zone{" +
                "zone='" + zone + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
