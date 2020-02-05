
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Region {
   /* @PrimaryKey(autoGenerate = true)
    public int id;*/
    
    @PrimaryKey
    @NonNull
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("territory")
    @Expose
    private String territory;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Override
    public String toString() {
        return "Region{" +
                "region='" + region + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
